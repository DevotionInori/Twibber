package com.SydShp.twibber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btHome;
    Button btMessage;
    Button btMe;

    Drawable icHome_select;
    Drawable icHome_unselected;
    Drawable icMessage_select;
    Drawable icMessage_unselected;
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
                btMessage.setCompoundDrawables(null,icMessage_unselected,null,null);
                changeFragment(new HomeFragment());
            }
        });

        btMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change icon image on click
                btHome.setCompoundDrawables(null,icHome_unselected,null,null);
                btMe.setCompoundDrawables(null,icMe_select,null,null);
                btMessage.setCompoundDrawables(null,icMessage_unselected,null,null);

            }
        });

        btMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change icon image on click
                btHome.setCompoundDrawables(null,icHome_unselected,null,null);
                btMe.setCompoundDrawables(null,icMe_unselected,null,null);
                btMessage.setCompoundDrawables(null,icMessage_select,null,null);

            }
        });

        btHome.setCompoundDrawables(null,icHome_select,null,null);
        btMe.setCompoundDrawables(null,icMe_unselected,null,null);
        btMessage.setCompoundDrawables(null,icMessage_unselected,null,null);
        changeFragment(new HomeFragment());
    }

    private void init(){

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        btHome = findViewById(R.id.btMain);
        btMessage = findViewById(R.id.btMessage);
        btMe = findViewById(R.id.btMe);

        icHome_select = getResources().getDrawable(R.drawable.ic_home);
        icHome_select.setBounds(0,0,icHome_select.getMinimumWidth(),icHome_select.getMinimumHeight());
        icHome_unselected = getResources().getDrawable(R.drawable.ic_home_unselected);
        icHome_unselected.setBounds(0,0,icHome_unselected.getMinimumWidth(),icHome_unselected.getMinimumHeight());
        icMessage_select = getResources().getDrawable(R.drawable.ic_message);
        icMessage_select.setBounds(0,0,icMessage_select.getMinimumWidth(),icMessage_select.getMinimumHeight());
        icMessage_unselected = getResources().getDrawable(R.drawable.ic_message_unselected);
        icMessage_unselected.setBounds(0,0,icMessage_unselected.getMinimumWidth(),icMessage_unselected.getMinimumHeight());
        icMe_select = getResources().getDrawable(R.drawable.ic_me);
        icMe_select.setBounds(0,0,icMe_select.getMinimumWidth(),icMe_select.getMinimumHeight());
        icMe_unselected = getResources().getDrawable(R.drawable.ic_me_unselected);
        icMe_unselected.setBounds(0,0,icMe_unselected.getMinimumWidth(),icMessage_unselected.getMinimumHeight());

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
