package com.SydShp.twibber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.text.TextUtils;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import android.os.Bundle;

import com.SydShp.twibber.model.User;

import org.litepal.LitePal;

import java.util.List;

public class SearchUserActivity extends AppCompatActivity {

    private String[] mStrs = {"aaa", "bbb", "ccc", "test"};
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
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    mListView.setFilterText(newText);
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
        String[] newMStr;
        for (int i=0;i<UserList.size();i++){

        }
    }
}
