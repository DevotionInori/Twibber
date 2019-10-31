package com.SydShp.twibber;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;

import android.text.TextUtils;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.SydShp.twibber.model.User;

import org.litepal.LitePal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchUserFragment extends Fragment {

    private ArrayList<String> mStrs = new ArrayList<String>();
    private Map<Integer,String> userMap = new HashMap<Integer, String>();
    private SearchView mSearchView;
    private ListView mListView;
    private Context mContext;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search_user,container,false);
        mSearchView = (SearchView) view.findViewById(R.id.userSearchView);
        mListView = (ListView) view.findViewById(R.id.userListView);
        mListView.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, mStrs));
        mListView.setTextFilterEnabled(true);




        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                changeMstr(query);
                List<User> queryUserList = LitePal.where("name like ?",query).find(User.class);
                if (queryUserList.isEmpty()){
                    Toast.makeText(mContext, "未找到该用户", Toast.LENGTH_SHORT).show();
                }else {
                    int idFound = queryUserList.get(0).getId();
                    Intent in = new Intent(mContext,HisOrHerHome.class);
                    in.putExtra("User",(Serializable)queryUserList.get(0));
                    mContext.startActivity(in);

                }
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    mListView.setFilterText(newText);
                    //mStrs.clear();
                    //userMap.clear();
                    changeMstr(newText);

                }else{
                    mListView.clearTextFilter();
                }
                return false;
            }
        });

        return  view;
    }


    void changeMstr(String likeString){
        List<User> UserList = LitePal.where("name like ?","%"+likeString+"%").find(User.class);
        mStrs.clear();
        for (int i=0;i<UserList.size();i++){
            mStrs.add(UserList.get(i).getName());
            mListView.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, mStrs));
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, mStrs.get(position), Toast.LENGTH_SHORT).show();
                    mSearchView.setQueryHint(mStrs.get(position));
                    int Tid = mSearchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
                    TextView textView = (TextView)mSearchView.findViewById(Tid);
                    textView.setText(mStrs.get(position));
                }
            });
            userMap.put(UserList.get(i).getId(),UserList.get(i).getName());
        }
    }
}
