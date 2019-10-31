package com.SydShp.twibber;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

public class MyPageFragment extends Fragment {

    private Context mContext;
    private RecyclerView recyclerView;
    private List<Twibber> data;
    private Button login;
    private TextView tip;
    private Toolbar toolbar;
    private CollapsingToolbarLayout  collapsingToolbarLayout;
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
        }
        else{
            collapsingToolbarLayout.setTitle("请先登录");
            tip.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            login.setText("登录");
        }
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


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login.getText()=="登出"){
                    SharedPreferences sp = mContext.getSharedPreferences("login",mContext.MODE_PRIVATE);
                    sp.edit().clear().apply();
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
        }
        else{
            toolbar.setTitle("请先登录");
            tip.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            login.setText("登录");
        }

        data=new ArrayList<Twibber>();
        data.add(new Twibber("Devotion","hhhhhhhhhhhhhhhhhhhhh"));
        data.add(new Twibber("Devotion","哈哈哈哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"
        ));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));



        QuickAdapter adapter = new QuickAdapter<Twibber>(data,mContext) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.twibber_item;
            }

            @Override
            public void convert(VH holder, Twibber data, int position) {
                holder.setText(R.id.nameText, data.getUsername());
                holder.setText(R.id.timeText,data.getTime());
                holder.setText(R.id.twibberContent,data.getContent());
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        adapter.addFooterView(LayoutInflater.from(mContext).inflate(R.layout.item_foot,null));
        recyclerView.addItemDecoration(new LinearItemDecoration(mContext, LinearLayoutManager.VERTICAL));
    }
}
