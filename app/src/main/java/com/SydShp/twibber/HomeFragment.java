package com.SydShp.twibber;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.SydShp.twibber.model.Comment;
import com.SydShp.twibber.model.LikeRelation;
import com.SydShp.twibber.model.Twibber;
import com.SydShp.twibber.model.UserRelation;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Twibber> data;
    private Context mContext;
    private ViewPager vp;
    private ViewPagerTitle hsv;
    private LinearLayout container;
    private String[] menus = { "关注","最新推博" };
    private DynamicLine dynamicLine;
    private TextView logInTip1;
    private TextView logInTip2;
    private QuickAdapter adapterFollow;
    private QuickAdapter adapterLatest;
    private PopupWindow popupWindow;
    private View contentView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }



    @Override
    public void onResume() {
        if(logInTip1!=null){
            data.clear();
            SharedPreferences sp =mContext.getSharedPreferences("login",Context.MODE_PRIVATE);
            if(sp.getString("username",null)!=null){
                logInTip1.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
            else{
                logInTip1.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);

            }
            if(sp.getInt("id",0)!=0){
                List<UserRelation> follow = LitePal.where("userId = ?",""+sp.getInt("id",0)).find(UserRelation.class);
                for (UserRelation i :
                        follow) {
                    List<Twibber> fTwibber = LitePal.where("publisherId=?",""+i.getFollowId()).find(Twibber.class);
                    data.addAll(fTwibber);
                }

                List<Twibber> mTwibber = LitePal.where("publisherId=?",""+sp.getInt("id",0)).find(Twibber.class);
                data.addAll(mTwibber);
            }
            adapterFollow.setmDatas(data);
            adapterLatest.setmDatas(LitePal.findAll(Twibber.class));
        }
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home_page,container,false);



        init(view);
        return  view;
    }


    public void initRecyclerView(View view, int position) {
        if (position % 2 == 0) {
            recyclerView = view.findViewById(R.id.recycle_home);

            logInTip1=view.findViewById(R.id.home_log_tip);

            data=new ArrayList<Twibber>();

            SharedPreferences sp =mContext.getSharedPreferences("login",Context.MODE_PRIVATE);
            if(sp.getString("username",null)!=null){
                recyclerView.setVisibility(View.VISIBLE);
                logInTip1.setVisibility(View.GONE);
            }
            else{
                logInTip1.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }




            if(sp.getInt("id",-1)!=-1){
                List<UserRelation> follow = LitePal.where("userId = ?",""+sp.getInt("id",0)).find(UserRelation.class);
                for (UserRelation i :
                        follow) {
                    List<Twibber> fTwibber = LitePal.where("publisherId=?",""+i.getFollowId()).find(Twibber.class);
                    data.addAll(fTwibber);
                }

                List<Twibber> mTwibber = LitePal.where("publisherId=?",""+sp.getInt("id",0)).find(Twibber.class);
                data.addAll(mTwibber);
            }


            adapterFollow = new QuickAdapter<Twibber>(data,mContext) {
                @Override
                public int getLayoutId(int viewType) {
                    return R.layout.twibber_item;
                }

                @Override
                public void convert(final VH holder, final Twibber data, int position) {
                    holder.setText(R.id.nameText, data.getUsername());
                    holder.setText(R.id.timeText,getThisTime(data.getDate()));
                    holder.setText(R.id.twibberContent,data.getContent());

                    final Twibber twibber = data;

                    SharedPreferences sp = mContext.getSharedPreferences("login",Context.MODE_PRIVATE);
                    final int uid=sp.getInt("id",-1);
                    if(uid!=-1){
                        List<LikeRelation> likes = LitePal.where("twibberId = ?",""+uid).find(LikeRelation.class);
                        for (LikeRelation i :
                                likes) {
                            if(i.getContenterId()==data.getId()){

                                holder.setImage(R.id.ib_like,R.drawable.ic_action_liked);
                            }
                        }
                    }

                    holder.getView(R.id.ib_conmment).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            contentView = LayoutInflater.from(mContext).inflate(
                                    R.layout.pop_edit_text, null);
                            popupWindow = new PopupWindow(contentView,
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT);
                            popupWindow.setFocusable(true);// 取得焦点
                            //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
                            popupWindow.setBackgroundDrawable(new BitmapDrawable());
                            //点击外部消失
                            popupWindow.setOutsideTouchable(true);
                            //设置可以点击
                            popupWindow.setTouchable(true);
                            //进入退出的动画，指定刚才定义的style
                            popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
                            popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
                            contentView.findViewById(R.id.btn_comment).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    EditText editText = contentView.findViewById(R.id.et_comment);
                                    Comment comment = new Comment();
                                    comment.setTwibberId(data.getId());
                                    comment.setCommenterId(uid);
                                    comment.setComment(editText.getText().toString());
                                    comment.save();
                                    Twibber twibber1 = LitePal.find(Twibber.class,twibber.getId());
                                    twibber1.setCommentCount(twibber1.getCommentCount()+1);
                                    twibber1.save();
                                    popupWindow.dismiss();
                                    Toast.makeText(mContext,"评论成功",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });

                    holder.getView(R.id.ib_like).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences sp = mContext.getSharedPreferences("login",Context.MODE_PRIVATE);
                            int uid=sp.getInt("id",-1);
                            if(uid!=-1){
                                List<LikeRelation> likes = LitePal.where("twibberId = ?",""+uid).find(LikeRelation.class);
                                boolean liked=false;
                                int deleteId=-1;
                                for (LikeRelation i :
                                        likes) {
                                    if(i.getContenterId()==twibber.getId()){
                                        liked=true;
                                        deleteId=i.getId();
                                    }
                                }
                                if(liked){
                                    Twibber twibber1 = LitePal.find(Twibber.class,twibber.getId());
                                    twibber1.setLikeCount(twibber1.getLikeCount()-1);
                                    twibber1.save();
                                    LitePal.delete(LikeRelation.class,deleteId);
                                    holder.setImage(R.id.ib_like,R.drawable.ic_action_like);
                                }else {
                                    Twibber twibber1 = LitePal.find(Twibber.class,twibber.getId());
                                    twibber1.setLikeCount(twibber1.getLikeCount()+1);
                                    twibber1.save();
                                    LikeRelation likeRelation = new LikeRelation();
                                    likeRelation.setTwibberId(uid);
                                    likeRelation.setContenterId(twibber.getId());
                                    likeRelation.save();
                                    holder.setImage(R.id.ib_like,R.drawable.ic_action_liked);
                                }
                            }
                        }
                    });
                }
            };
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(adapterFollow);
            adapterFollow.addFooterView(LayoutInflater.from(mContext).inflate(R.layout.item_foot,null));
            adapterFollow.addHeaderView(LayoutInflater.from(mContext).inflate(R.layout.item_head,null));
            recyclerView.addItemDecoration(new LinearItemDecoration(mContext, LinearLayoutManager.VERTICAL));

        } else {
            recyclerView = view.findViewById(R.id.recycle_home);
            logInTip2=view.findViewById(R.id.home_log_tip);
            logInTip2.setVisibility(View.GONE);

            data=new ArrayList<Twibber>();

            List<Twibber> allTwibber = LitePal.findAll(Twibber.class);
            data.addAll(allTwibber);


            adapterLatest = new QuickAdapter<Twibber>(data,mContext) {
                @Override
                public int getLayoutId(int viewType) {
                    return R.layout.twibber_item;
                }

                @Override
                public void convert(final VH holder, final Twibber data, int position) {
                    holder.setText(R.id.nameText, data.getUsername());
                    holder.setText(R.id.timeText,getThisTime(data.getDate()));
                    holder.setText(R.id.twibberContent,data.getContent());

                    final Twibber twibber = data;

                    SharedPreferences sp = mContext.getSharedPreferences("login",Context.MODE_PRIVATE);
                    final int uid=sp.getInt("id",-1);
                    if(uid!=-1){
                        List<LikeRelation> likes = LitePal.where("twibberId = ?",""+uid).find(LikeRelation.class);
                        for (LikeRelation i :
                                likes) {
                            if(i.getContenterId()==data.getId()){

                                holder.setImage(R.id.ib_like,R.drawable.ic_action_liked);
                            }
                        }
                    }

                    holder.getView(R.id.ib_conmment).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            contentView = LayoutInflater.from(mContext).inflate(
                                    R.layout.pop_edit_text, null);
                            popupWindow = new PopupWindow(contentView,
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT);
                            popupWindow.setFocusable(true);// 取得焦点
                            //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
                            popupWindow.setBackgroundDrawable(new BitmapDrawable());
                            //点击外部消失
                            popupWindow.setOutsideTouchable(true);
                            //设置可以点击
                            popupWindow.setTouchable(true);
                            //进入退出的动画，指定刚才定义的style
                            popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
                            popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
                            contentView.findViewById(R.id.btn_comment).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    EditText editText = contentView.findViewById(R.id.et_comment);
                                    Comment comment = new Comment();
                                    comment.setTwibberId(data.getId());
                                    comment.setCommenterId(uid);
                                    comment.setComment(editText.getText().toString());
                                    comment.save();
                                    Twibber twibber1 = LitePal.find(Twibber.class,twibber.getId());
                                    twibber1.setCommentCount(twibber1.getCommentCount()+1);
                                    twibber1.save();
                                    popupWindow.dismiss();
                                    Toast.makeText(mContext,"评论成功",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });

                    holder.getView(R.id.ib_like).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences sp = mContext.getSharedPreferences("login",Context.MODE_PRIVATE);
                            int uid=sp.getInt("id",-1);
                            if(uid!=-1){
                                List<LikeRelation> likes = LitePal.where("twibberId = ?",""+uid).find(LikeRelation.class);
                                boolean liked=false;
                                int deleteId=-1;
                                for (LikeRelation i :
                                        likes) {
                                    if(i.getContenterId()==twibber.getId()){
                                        liked=true;
                                        deleteId=i.getId();
                                    }
                                }
                                if(liked){
                                    Twibber twibber1 = LitePal.find(Twibber.class,twibber.getId());
                                    twibber1.setLikeCount(twibber1.getLikeCount()-1);
                                    twibber1.save();
                                    LitePal.delete(LikeRelation.class,deleteId);
                                    holder.setImage(R.id.ib_like,R.drawable.ic_action_like);
                                }else {
                                    Twibber twibber1 = LitePal.find(Twibber.class,twibber.getId());
                                    twibber1.setLikeCount(twibber1.getLikeCount()+1);
                                    twibber1.save();
                                    LikeRelation likeRelation = new LikeRelation();
                                    likeRelation.setTwibberId(uid);
                                    likeRelation.setContenterId(twibber.getId());
                                    likeRelation.save();
                                    holder.setImage(R.id.ib_like,R.drawable.ic_action_liked);
                                }
                            }
                        }
                    });


                }
            };
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(adapterLatest);
            adapterLatest.addFooterView(LayoutInflater.from(mContext).inflate(R.layout.item_foot,null));
            adapterLatest.addHeaderView(LayoutInflater.from(mContext).inflate(R.layout.item_head,null));
            recyclerView.addItemDecoration(new LinearItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        }

    }


    private void init(View view){
        vp=(ViewPager)view.findViewById(R.id.view_page_home);



        ArrayList<View> views = new ArrayList<>();
        hsv = (ViewPagerTitle) view.findViewById(R.id.horizontal_SV);
        hsv.initData(new String[]{"关注", "最新推博"}, vp, 0);

        LayoutInflater inflater = getLayoutInflater();
        views = new ArrayList<>();
        views.add(inflater.inflate(R.layout.recycler_view, null));
        views.add(inflater.inflate(R.layout.recycler_view, null));

        MyPagerAdapter adapter = new MyPagerAdapter(views){
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                initRecyclerView((View)super.instantiateItem(container, position),position);
                return super.instantiateItem(container, position);
            }
        };
        vp.setAdapter(adapter);
    }




    public static String getThisTime(Date date) {

        Date dateNow = new Date();
        long delta = dateNow.getTime() - date.getTime();
        //Calendar calendar = Calendar.getInstance();
        String t = "";
        if (delta < 60000) {
            t += "刚刚";
        } else if (delta < 3600 * 1000) {
            long d = delta / 60000;
            t += d;
            t += "分钟前";
        } else if (delta < 3600 * 10 * 1000) {
            long d = delta / (3600 * 1000);
            t += d;
            t += "小时前";
        } else {
            SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
            SimpleDateFormat hm = new SimpleDateFormat("hh:mm");
            if (yyyy.format(date) != yyyy.format(dateNow)) {
                t += yyyy.format(date);
                t += "年";
            }
            if (ymd.format(date) != ymd.format(dateNow)) {
                SimpleDateFormat md = new SimpleDateFormat("MM月dd日");
                t += md.format(date);
            } else {
                t += hm.format(date);
            }
        }
        return t;
    }

}
