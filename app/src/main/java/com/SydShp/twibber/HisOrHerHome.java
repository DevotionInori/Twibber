package com.SydShp.twibber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
            public void convert(VH holder, Twibber data, int position) {
                holder.setText(R.id.nameText, data.getUsername());
                holder.setText(R.id.timeText,getThisTime(data.getDate()));
                holder.setText(R.id.twibberContent,data.getContent());
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.item_foot,null));
        mRecyclerView.addItemDecoration(new LinearItemDecoration(this,LinearLayoutManager.VERTICAL));
    }
}
