package com.SydShp.twibber;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.SydShp.twibber.model.User;

import org.litepal.LitePal;

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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
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
            data=new ArrayList<Twibber>();
            data.add(new Twibber("DevotionInori","hhhhhhhhhhhhhhhhhhhhh"));
            data.add(new Twibber("Devoti","哈哈哈哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            data.add(new Twibber("Devtion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            data.add(new Twibber("Deotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            data.add(new Twibber("Deotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            data.add(new Twibber("Deotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            data.add(new Twibber("Deotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            data.add(new Twibber("Deotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            data.add(new Twibber("Deotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            data.add(new Twibber("Deotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            data.add(new Twibber("Detion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            data.add(new Twibber("Deotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            data.add(new Twibber("Deotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            data.add(new Twibber("Deotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));



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
            adapter.addHeaderView(LayoutInflater.from(mContext).inflate(R.layout.item_head,null));
            recyclerView.addItemDecoration(new LinearItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        } else {
            recyclerView = view.findViewById(R.id.recycle_home);
            data=new ArrayList<Twibber>();
            //data.add(new Twibber("Devotion","hhhhhhhhhhhhhhhhhhhhh"));
            //data.add(new Twibber("Devotion","哈哈哈哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            //data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            //data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            //data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            //data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            //data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            //data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            //data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            //data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            //data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            //data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            //data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            //data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            //data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            //data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));
            //data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或"));

            List<User> allSongs = LitePal.findAll(User.class);
            for(int i=0;i<allSongs.size();i++)
            {
                data.add(new Twibber(allSongs.get(i).getLoginId(),allSongs.get(i).getPassWd()));
            }


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
            adapter.addHeaderView(LayoutInflater.from(mContext).inflate(R.layout.item_head,null));
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

}
