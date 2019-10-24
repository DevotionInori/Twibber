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

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<String> data;
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
        data=new ArrayList<String>();
        data.add("Devotion");
        data.add("Devotion");
        data.add("Devotion");
        data.add("Devotion");
        data.add("Devotion");
        data.add("Devotion");
        data.add("Devotion");
        data.add("Devotion");
        data.add("Devotion");
        data.add("Devotion");
        data.add("Devotion");
        data.add("Devotion");
        data.add("Devotion");
        data.add("Devotion");


        QuickAdapter adapter = new QuickAdapter<String>(data) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.twibber_item;
            }

            @Override
            public void convert(VH holder, String data, int position) {
                holder.setText(R.id.nameText, data);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
    }


}
