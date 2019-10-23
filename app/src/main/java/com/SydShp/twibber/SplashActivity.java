package com.SydShp.twibber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_DISPLAY_LENGHT= 3000;    //延迟2秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);	//第二个参数即为执行完跳转后的Activity
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                SplashActivity.this.finish();   //关闭SplashActivity，将其回收，否则按返回键会返回此界面
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}
