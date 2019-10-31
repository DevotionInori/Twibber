package com.SydShp.twibber;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.SydShp.twibber.model.Twibber;

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }


    @Override
    public void onResume() {
        if(logInTip1!=null){

            SharedPreferences sp =mContext.getSharedPreferences("login",Context.MODE_PRIVATE);
            if(sp.getString("username",null)!=null)
                logInTip1.setVisibility(View.GONE);
            else
                logInTip1.setVisibility(View.VISIBLE);
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

            SharedPreferences sp =mContext.getSharedPreferences("login",Context.MODE_PRIVATE);
            if(sp.getString("username",null)!=null)
                logInTip1.setVisibility(View.GONE);
            else
                logInTip1.setVisibility(View.VISIBLE);


            data=new ArrayList<Twibber>();

            data=new ArrayList<Twibber>();
            List<Twibber> allTwibber = LitePal.findAll(Twibber.class);
            data.addAll(allTwibber);

            QuickAdapter adapter = new QuickAdapter<Twibber>(data,mContext) {
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
            adapter.addHeaderView(LayoutInflater.from(mContext).inflate(R.layout.item_head,null));
            recyclerView.addItemDecoration(new LinearItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        } else {
            recyclerView = view.findViewById(R.id.recycle_home);
            logInTip2=view.findViewById(R.id.home_log_tip);
            logInTip2.setVisibility(View.GONE);

            data=new ArrayList<Twibber>();
            List<Twibber> allTwibber = LitePal.findAll(Twibber.class);
            data.addAll(allTwibber);


            QuickAdapter adapter = new QuickAdapter<Twibber>(data,mContext) {
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
