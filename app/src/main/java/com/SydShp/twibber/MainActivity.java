package com.SydShp.twibber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    Button btHome;
    Button btSearch;
    Button btMe;

    private FloatingActionButton faButton;

    Drawable icHome_select;
    Drawable icHome_unselected;
    Drawable icSearch_select;
    Drawable icSearch_unselected;
    Drawable icMe_select;
    Drawable icMe_unselected;

    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change icon image on click
                btHome.setCompoundDrawables(null,icHome_select,null,null);
                btMe.setCompoundDrawables(null,icMe_unselected,null,null);
                btSearch.setCompoundDrawables(null,icSearch_unselected,null,null);
                changeFragment(new HomeFragment());
            }
        });

        btMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change icon image on click
                btHome.setCompoundDrawables(null,icHome_unselected,null,null);
                btMe.setCompoundDrawables(null,icMe_select,null,null);
                btSearch.setCompoundDrawables(null,icSearch_unselected,null,null);
                changeFragment(new MyPageFragment());

            }
        });

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change icon image on click
                btHome.setCompoundDrawables(null,icHome_unselected,null,null);
                btMe.setCompoundDrawables(null,icMe_unselected,null,null);
                btSearch.setCompoundDrawables(null,icSearch_select,null,null);

            }
        });

        faButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
                if(sp.getString("username",null)==null)
                    Toast.makeText(MainActivity.this,"请先登录",Toast.LENGTH_LONG).show();
                else{
                    Intent in = new Intent(MainActivity.this,AddTwibber.class);
                    startActivity(in);
                }
            }
        });


        btHome.setCompoundDrawables(null,icHome_select,null,null);
        btMe.setCompoundDrawables(null,icMe_unselected,null,null);
        btSearch.setCompoundDrawables(null,icSearch_unselected,null,null);
        changeFragment(new HomeFragment());
    }

    private void init(){

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        btHome = findViewById(R.id.btMain);
        btSearch = findViewById(R.id.btSearch);
        btMe = findViewById(R.id.btMe);
        faButton=findViewById(R.id.add_twibber);

        icHome_select = getResources().getDrawable(R.drawable.ic_home);
        icHome_select.setBounds(0,0,icHome_select.getMinimumWidth(),icHome_select.getMinimumHeight());
        icHome_unselected = getResources().getDrawable(R.drawable.ic_home_unselected);
        icHome_unselected.setBounds(0,0,icHome_unselected.getMinimumWidth(),icHome_unselected.getMinimumHeight());
        icSearch_select = getResources().getDrawable(R.drawable.ic_action_search);
        icSearch_select.setBounds(0,0,icSearch_select.getMinimumWidth(),icSearch_select.getMinimumHeight());
        icSearch_unselected = getResources().getDrawable(R.drawable.ic_action_search_unselected);
        icSearch_unselected.setBounds(0,0,icSearch_unselected.getMinimumWidth(),icSearch_unselected.getMinimumHeight());
        icMe_select = getResources().getDrawable(R.drawable.ic_me);
        icMe_select.setBounds(0,0,icMe_select.getMinimumWidth(),icMe_select.getMinimumHeight());
        icMe_unselected = getResources().getDrawable(R.drawable.ic_me_unselected);
        icMe_unselected.setBounds(0,0,icMe_unselected.getMinimumWidth(),icSearch_unselected.getMinimumHeight());

    }

    private void changeFragment(Fragment fragment){
        //实例化碎片管理器对象
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        //选择fragment替换的部分
        ft.replace(R.id.content,fragment);
        ft.commit();
    }

}
