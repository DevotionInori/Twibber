package com.SydShp.twibber;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.SydShp.twibber.model.Comment;
import com.SydShp.twibber.model.LikeRelation;
import com.SydShp.twibber.model.Twibber;
import com.SydShp.twibber.model.User;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.litepal.LitePal;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyPageFragment extends Fragment {

    private Context mContext;
    private RecyclerView recyclerView;
    private List<Twibber> data;
    private Button login;
    private TextView tip;
    private Toolbar toolbar;
    private CollapsingToolbarLayout  collapsingToolbarLayout;
    private QuickAdapter adapter;
    private TextView fansCount;
    private TextView followCount;
    private PopupWindow popupWindow;
    private View contentView;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public void onResume() {
        SharedPreferences sp = mContext.getSharedPreferences("login",mContext.MODE_PRIVATE);
        if(sp.getString("username",null)!=null){
            collapsingToolbarLayout.setTitle(sp.getString("username",null));
            tip.setVisibility(View.GONE);
            login.setText("登出");
            recyclerView.setVisibility(View.VISIBLE);
            User user = LitePal.find(User.class,sp.getInt("id",0));
            fansCount.setVisibility(View.VISIBLE);
            followCount.setVisibility(View.VISIBLE);
            fansCount.setText("粉丝:"+user.getFans_count());
            followCount.setText("关注:"+user.getFollow_count());
        }
        else{
            collapsingToolbarLayout.setTitle("请先登录");
            tip.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            login.setText("登录");
            fansCount.setVisibility(View.GONE);
            followCount.setVisibility(View.GONE);
        }
        List<Twibber> mTwibber;
        if(sp.getInt("id",-1)!=-1){
            data.clear();
            mTwibber = LitePal.where("publisherID = ?", ""+sp.getInt("id",0)).find(Twibber.class);
            data.addAll(mTwibber);
        }
        adapter.notifyDataSetChanged();
        adapter.setmDatas(data);
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_me,container,false);
        init(view);
        return  view;
    }




    private void init(View view){
        recyclerView = view.findViewById(R.id.recycle_me);
        login=view.findViewById(R.id.log_in);
        tip=view.findViewById(R.id.empty_rep_text);
        toolbar=view.findViewById(R.id.toolbar);
        collapsingToolbarLayout=view.findViewById(R.id.mCollapsingToolbarLayout);
        fansCount=view.findViewById(R.id.my_fans);
        followCount=view.findViewById(R.id.my_follows);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login.getText()=="登出"){
                    SharedPreferences sp = mContext.getSharedPreferences("login",mContext.MODE_PRIVATE);
                    sp.edit().remove("id").apply();
                    sp.edit().remove("username").apply();
                }
                Intent in = new Intent(mContext,LogIn.class);
                startActivity(in);
            }
        });

        SharedPreferences sp = mContext.getSharedPreferences("login",mContext.MODE_PRIVATE);
        if(sp.getString("username",null)!=null){
            toolbar.setTitle(sp.getString("username",null));
            tip.setVisibility(View.GONE);
            login.setText("登出");
            recyclerView.setVisibility(View.VISIBLE);
            User user = LitePal.find(User.class,sp.getInt("id",0));
            String s = "粉丝:"+user.getFans_count();
            fansCount.setText(s);
            s = "关注:"+user.getFollow_count();
            followCount.setText(s);
        }
        else{
            toolbar.setTitle("请先登录");
            tip.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            login.setText("登录");
        }


        data=new ArrayList<Twibber>();
        List<Twibber> mTwibber;
        if(sp.getInt("id",-1)!=-1){
            mTwibber = LitePal.where("publisherID = ?", ""+sp.getInt("id",0)).find(Twibber.class);
            data.addAll(mTwibber);
        }


        adapter = new QuickAdapter<Twibber>(data,mContext) {
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

                holder.getView(R.id.ib_transfer_twibber_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(mContext,AddTwibber.class);
                        in.putExtra("TransferTwibber",(Serializable) data);
                        mContext.startActivity(in);
                    }
                });

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
        recyclerView.setAdapter(adapter);
        adapter.addFooterView(LayoutInflater.from(mContext).inflate(R.layout.item_foot,null));
        recyclerView.addItemDecoration(new LinearItemDecoration(mContext, LinearLayoutManager.VERTICAL));
    }

    public String getThisTime(Date date) {

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
