package com.SydShp.twibber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import java.util.ArrayList;

public class LogIn extends AppCompatActivity {

    private Button btnLogIn;
    private Button btnLogOn;
    private Button btnReg;
    private ViewPager vp;
    private ViewPagerTitle hsv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        init();

        ConstraintLayout layout= (ConstraintLayout)findViewById(R.id.cl_log_in);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                return false;
            }
        });


    }

    private void init(){
        vp=(ViewPager)findViewById(R.id.view_page_log);
        ArrayList<View> views = new ArrayList<>();
        hsv = (ViewPagerTitle)findViewById(R.id.log_vpTitle);
        hsv.initData(new String[]{"登录", "注册"}, vp, 0);

        LayoutInflater inflater = getLayoutInflater();
        views = new ArrayList<>();
        views.add(inflater.inflate(R.layout.page_log_in, null));
        views.add(inflater.inflate(R.layout.page_register, null));




        MyPagerAdapter adapter = new MyPagerAdapter(views){
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                initPage((View)super.instantiateItem(container, position),position);
                return super.instantiateItem(container, position);
            }
        };
        vp.setAdapter(adapter);
    }

    public void initPage(View view, int position) {
        if (position % 2 == 0) {
            btnLogIn=view.findViewById(R.id.btn_log_in);
        } else {
            btnLogOn=view.findViewById(R.id.btn_register);
        }
    }


}
