package com.SydShp.twibber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class LogIn extends AppCompatActivity {

    private Button btnLogIn;
    private Button btnLogOn;
    private Button btnReg;


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

        btnReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(btnLogIn.getVisibility()==View.VISIBLE){
                    btnReg.setText("登录");
                    btnLogIn.setVisibility(View.GONE);
                    btnLogOn.setVisibility(View.VISIBLE);
                }else{
                    btnReg.setText("注册");
                    btnLogIn.setVisibility(View.VISIBLE);
                    btnLogOn.setVisibility(View.GONE);
                }
            }
        });

    }

    private void init(){
        btnLogIn=findViewById(R.id.btn_log_in);
        btnLogOn=findViewById(R.id.btn_log_on);
        btnReg=findViewById(R.id.btn_register);
    }


}
