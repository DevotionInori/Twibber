package com.SydShp.twibber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.text.TextUtils;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import android.os.Bundle;
import android.widget.Toast;

import com.SydShp.twibber.model.User;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchUserActivity extends AppCompatActivity {

    private ArrayList<String> mStrs;
    private Map<Integer,String> userMap;
    private SearchView mSearchView;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        mSearchView = (SearchView) findViewById(R.id.userSearchView);
        mListView = (ListView) findViewById(R.id.userListView);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrs));
        mListView.setTextFilterEnabled(true);

        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                changeMstr(query);
                List<User> queryUserList = LitePal.where("name like ?",query).find(User.class);
                if (queryUserList.isEmpty()){
                    Toast.makeText(SearchUserActivity.this, "未找到该用户", Toast.LENGTH_SHORT).show();
                }else {
                    int idFound = queryUserList.get(0).getId();
                    Toast.makeText(SearchUserActivity.this, idFound, Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    mListView.setFilterText(newText);
                    mStrs.clear();
                    userMap.clear();
                    changeMstr(newText);

                }else{
                    mListView.clearTextFilter();
                }
                return false;
            }
        });

    }

    void changeMstr(String likeString){
        List<User> UserList = LitePal.where("name like %?%",likeString).find(User.class);
        for (int i=0;i<UserList.size();i++){
            mStrs.add(UserList.get(i).getName());
            userMap.put(UserList.get(i).getId(),UserList.get(i).getName());
        }
    }
}
