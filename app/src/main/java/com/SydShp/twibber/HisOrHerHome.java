package com.SydShp.twibber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.SydShp.twibber.model.Comment;
import com.SydShp.twibber.model.LikeRelation;
import com.SydShp.twibber.model.Twibber;
import com.SydShp.twibber.model.User;
import com.SydShp.twibber.model.UserRelation;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.litepal.LitePal;

import java.util.List;

import static com.SydShp.twibber.HomeFragment.getThisTime;

public class HisOrHerHome extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RecyclerView mRecyclerView;
    private List<Twibber> data;
    private User user;
    private Button btnFollow;
    private Twibber twibber;
    private TextView fansCount;
    private TextView followCount;
    private PopupWindow popupWindow;
    private View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_or_her_home);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        init();

        List<UserRelation> ur = LitePal.where("userId = ? and followId = ?",""+getSharedPreferences("login",MODE_PRIVATE).getInt("id",0),
                ""+user.getId()).find(UserRelation.class);
        if(!ur.isEmpty()){
            btnFollow.setText("已关注");
            btnFollow.setBackground(getDrawable(R.drawable.btn_followed));
        }

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<UserRelation> urs = LitePal.where("userId = ? and followId = ?",""+getSharedPreferences("login",MODE_PRIVATE).getInt("id",0),
                        ""+user.getId()).find(UserRelation.class);
                if(!urs.isEmpty()){
                    LitePal.delete(UserRelation.class,urs.get(0).getId());
                    User follower = LitePal.find(User.class,getSharedPreferences("login",MODE_PRIVATE).getInt("id",0));
                    follower.setFollow_count(follower.getFollow_count()-1);
                    follower.save();
                    User followed = LitePal.find(User.class,user.getId());
                    followed.setFans_count(followed.getFans_count()-1);
                    followed.save();
                    btnFollow.setText("关注");
                    btnFollow.setBackground(getDrawable(R.drawable.btn_true_blue));

                    user= LitePal.find(User.class,twibber.getPublisherID());
                    fansCount.setText("粉丝:"+user.getFans_count());
                    followCount.setText("关注:"+user.getFollow_count());
                }else {
                    UserRelation ur=new UserRelation();
                    ur.setUserId(getSharedPreferences("login",MODE_PRIVATE).getInt("id",0));
                    ur.setFollowId(user.getId());
                    ur.save();
                    User follower = LitePal.find(User.class,getSharedPreferences("login",MODE_PRIVATE).getInt("id",0));
                    follower.setFollow_count(follower.getFollow_count()+1);
                    follower.save();
                    User followed = LitePal.find(User.class,user.getId());
                    followed.setFans_count(followed.getFans_count()+1);
                    followed.save();
                    btnFollow.setText("已关注");
                    btnFollow.setBackground(getDrawable(R.drawable.btn_followed));

                    user= LitePal.find(User.class,twibber.getPublisherID());
                    fansCount.setText("粉丝:"+user.getFans_count());
                    followCount.setText("关注:"+user.getFollow_count());
                }
            }
        });

    }

    private void init(){
        collapsingToolbarLayout=findViewById(R.id.HCollapsingToolbarLayout);
        mRecyclerView=findViewById(R.id.recycle_HH);
        btnFollow=findViewById(R.id.follow_H);
        fansCount=findViewById(R.id.hh_fans);
        followCount=findViewById(R.id.hh_follows);


        user=(User)getIntent().getSerializableExtra("User");

        fansCount.setText("粉丝:"+user.getFans_count());
        followCount.setText("关注:"+user.getFollow_count());

        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
        if(user.getId()==sp.getInt("id",0))
            btnFollow.setVisibility(View.GONE);

        collapsingToolbarLayout.setTitle(user.getName());



        data=LitePal.where("publisherId = ?",""+user.getId()).find(Twibber.class);

        QuickAdapter adapter = new QuickAdapter<Twibber>(data,this) {
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

                SharedPreferences sp =getSharedPreferences("login", Context.MODE_PRIVATE);
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
                        contentView = LayoutInflater.from(HisOrHerHome.this).inflate(
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
                                Toast.makeText(HisOrHerHome.this,"评论成功",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

                holder.getView(R.id.ib_like).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sp = getSharedPreferences("login",Context.MODE_PRIVATE);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.item_foot,null));
        mRecyclerView.addItemDecoration(new LinearItemDecoration(this,LinearLayoutManager.VERTICAL));
    }
}
