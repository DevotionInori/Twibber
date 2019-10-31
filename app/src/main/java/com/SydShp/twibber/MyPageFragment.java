package com.SydShp.twibber;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.SydShp.twibber.model.Twibber;
import com.SydShp.twibber.model.User;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.litepal.LitePal;

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
            public void convert(VH holder, Twibber data, int position) {
                holder.setText(R.id.nameText, data.getUsername());
                holder.setText(R.id.timeText,getThisTime(data.getDate()));
                holder.setText(R.id.twibberContent,data.getContent());
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
