package com.SydShp.twibber;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.SydShp.twibber.model.Twibber;
import com.SydShp.twibber.model.User;

import org.litepal.LitePal;

public class TwibberPage extends AppCompatActivity {

    private Toolbar mtoolbar;
    private TextView TwibberUserName;
    private TextView publishDate;
    private TextView TwibberContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twibber_page);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        mtoolbar = findViewById(R.id.twibber_toolbar);

        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        init();

    }

    private void init(){
        TwibberUserName = findViewById(R.id.nameText_twibber);
        publishDate = findViewById(R.id.timeText_twibber);
        TwibberContent = findViewById(R.id.twibberContent_twibber);


        Twibber twibber = (Twibber)getIntent().getSerializableExtra("twibber");

        TwibberUserName.setText(LitePal.find(User.class,twibber.getPublisherID()).getName());
        publishDate.setText(HomeFragment.getThisTime(twibber.getDate()));
        TwibberContent.setText(twibber.getContent());
    }


}
