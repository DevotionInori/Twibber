package com.SydShp.twibber;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.WeakHashMap;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Twibber> data;
    private Context mContext;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_home_page,container,false);
        init(view);
        return  view;
    }

    private void init(View view){
        recyclerView = view.findViewById(R.id.recycle_home);
        data=new ArrayList<Twibber>();
        data.add(new Twibber("Devotion","hhhhhhhhhhhhhhhhhhhhh",2019,10,24,14,14));
        data.add(new Twibber("Devotion","哈哈哈哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或",
                2019,10,24,14,14));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或",
                2019,10,24,14,22));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或",
                2019,10,24,14,22));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或",
                2019,10,24,14,22));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或",
                2019,10,24,14,22));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或",
                2019,10,24,14,22));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或",
                2019,10,24,14,22));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或",
                2019,10,24,14,22));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或",
                2019,10,24,14,22));data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或",
                2019,10,24,14,22));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或",
                2019,10,24,14,22));
        data.add(new Twibber("Devotion","哈哈哈大师法大使馆还是当天分公司的附件一染色很关键考核人特级合格不让他以改了欲哭俄日月入而不服而过故意如果附件舍不得放寒假刚打算要打防结合哈或或哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈和或或或或或或或",
                2019,10,24,14,22));


        QuickAdapter adapter = new QuickAdapter<Twibber>(data) {
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
        recyclerView.addItemDecoration(new LinearItemDecoration(mContext, LinearLayoutManager.VERTICAL));
    }


}
