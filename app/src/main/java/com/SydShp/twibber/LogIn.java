package com.SydShp.twibber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.SydShp.twibber.model.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class LogIn extends AppCompatActivity {

    private Button btnLogIn;
    private Button btnRegister;
    private ViewPager vp;
    private ViewPagerTitle hsv;
    private TextInputEditText idLogIn;
    private TextInputEditText pwdLogIn;
    private TextInputEditText idRegister;
    private TextInputEditText pwdRegister;
    private TextInputEditText nameRegister;

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

        SQLiteDatabase db = LitePal.getDatabase();

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
        if (position == 0) {
            btnLogIn=view.findViewById(R.id.btn_log_in);
            idLogIn=view.findViewById(R.id.log_in_uesrid);
            pwdLogIn=view.findViewById(R.id.log_in_password);

            SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
            idLogIn.setText(sp.getString("lastLogId",null));

            btnLogIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(idLogIn.getText())) {
                        Toast.makeText(LogIn.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(pwdLogIn.getText())){
                        Toast.makeText(LogIn.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    }else {
                        List<User> user = LitePal.where("loginId = ?", idLogIn.getText().toString()).find(User.class);
                        if (user.isEmpty()) {
                            Toast.makeText(LogIn.this, "用户不存在！", Toast.LENGTH_SHORT).show();
                        }else if (user.get(0).getPassWd().equals(pwdLogIn.getText().toString())){
                            //todo:宋亚东来写登录功能
                            SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("username",user.get(0).getName()).apply();
                            editor.putString("lastLogId",user.get(0).getLoginId()).apply();
                            editor.putString("id",user.get(0).getLoginId()).apply();
                            LogIn.this.finish();
                        }else{
                            Toast.makeText(LogIn.this, "密码错误!", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
        } else {
            btnRegister =view.findViewById(R.id.btn_register);
            idRegister=view.findViewById(R.id.register_uesrid);
            pwdRegister=view.findViewById(R.id.register_password);
            nameRegister=view.findViewById(R.id.register_name);
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(idRegister.getText())) {
                        Toast.makeText(LogIn.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(pwdRegister.getText())){
                        Toast.makeText(LogIn.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    }else if (TextUtils.isEmpty(nameRegister.getText())){
                        Toast.makeText(LogIn.this, "请输入昵称", Toast.LENGTH_SHORT).show();
                    }else {
                        List<User> tryUser = LitePal.where("loginId = ? ",idRegister.getText().toString()).find(User.class);
                        //Toast.makeText(LogIn.this, tryUser.size(), Toast.LENGTH_SHORT).show();
                        if (!tryUser.isEmpty()) {
                            Toast.makeText(LogIn.this,"用户名已存在!",Toast.LENGTH_LONG).show();
                        }else {
                            User regUser = new User();
                            regUser.setLoginId(idRegister.getText().toString());
                            regUser.setPassWd(pwdRegister.getText().toString());
                            regUser.setName(nameRegister.getText().toString());
                            regUser.save();

                            pwdRegister.setText("");
                            nameRegister.setText("");
                            Toast.makeText(LogIn.this,"注册成功!",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    }


}
