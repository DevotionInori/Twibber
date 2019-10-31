package com.SydShp.twibber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.SydShp.twibber.model.Twibber;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class AddTwibber extends AppCompatActivity {

    private Button cancel;
    private Button publicTb;
    private TextView UserName;
    private TextInputEditText inputEditText;
    private Twibber tranfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_twibber);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        init();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTwibber.this.finish();
            }
        });

        publicTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
                Twibber tTwibber = new Twibber();
                tTwibber.setUsername(sp.getString("username",null));
                tTwibber.setContent(inputEditText.getText().toString());
                tTwibber.setPublisherID(sp.getInt("id",0));
                tTwibber.setDate(new Date());
                tTwibber.setLikeCount(0);
                tTwibber.setCommentCount(0);
                tTwibber.save();
                AddTwibber.this.finish();
            }
        });

    }

    private void init(){
        cancel=findViewById(R.id.btn_cancel);
        publicTb=findViewById(R.id.btn_public);
        UserName=findViewById(R.id.add_name);
        inputEditText=findViewById(R.id.twibber_content);

        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
        UserName.setText(sp.getString("username",null));

        if(getIntent().hasExtra("TransferTwibber")){
            tranfer=(Twibber)getIntent().getSerializableExtra("TransferTwibber");
            inputEditText.setText("@"+tranfer.getUsername()+":"+tranfer.getContent());
        }
    }
}
