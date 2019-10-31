package com.SydShp.twibber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.SydShp.twibber.model.Comment;
import com.SydShp.twibber.model.LikeRelation;
import com.SydShp.twibber.model.Twibber;
import com.SydShp.twibber.model.User;

import org.litepal.LitePal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TwibberPage extends AppCompatActivity {

    private Toolbar mtoolbar;
    private RecyclerView rvComment;
    private TextView TwibberUserName;
    private TextView publishDate;
    private TextView TwibberContent;
    private ImageButton btnLike;
    private List<Comment> data;
    private int uid;
    private Twibber twibber;
    private TextView tvComment;
    private TextView tvLike;
    private PopupWindow popupWindow;
    private View contentView;
    private CommentAdapter adapter;
    private ImageButton ibComment;
    private NiceImageView avator;
    private TextView tvName;

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


        avator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(TwibberPage.this,HisOrHerHome.class);
                in.putExtra("User",(Serializable)LitePal.find(User.class,twibber.getPublisherID()));
                startActivity(in);
            }
        });

        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(TwibberPage.this,HisOrHerHome.class);
                in.putExtra("User",(Serializable)LitePal.find(User.class,twibber.getPublisherID()));
                startActivity(in);
            }
        });




        ibComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentView = LayoutInflater.from(TwibberPage.this).inflate(
                        R.layout.pop_edit_text, null);
                popupWindow = new PopupWindow(contentView,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);// 取得焦点
                //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                //点击外部消失
                popupWindow.setOutsideTouchable(true);
                //设置可以点击
                popupWindow.setTouchable(true);
                //进入退出的动画，指定刚才定义的style
                popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
                popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
                contentView.findViewById(R.id.btn_comment).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText editText = contentView.findViewById(R.id.et_comment);
                        Comment comment = new Comment();
                        comment.setTwibberId(twibber.getId());
                        comment.setCommenterId(uid);
                        comment.setComment(editText.getText().toString());
                        comment.save();
                        Twibber twibber1 = LitePal.find(Twibber.class,twibber.getId());
                        twibber1.setCommentCount(twibber1.getCommentCount()+1);
                        twibber1.save();
                        popupWindow.dismiss();
                        Toast.makeText(TwibberPage.this,"评论成功",Toast.LENGTH_LONG).show();
                        data.add(comment);
                        adapter.setmDatas(data);
                        tvComment.setText("评论 "+twibber1.getCommentCount());
                    }
                });
            }
        });

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<LikeRelation> likeRelations = LitePal.where("twibberId = ? and contenterId = ?",""+uid,""+twibber.getId()).find(LikeRelation.class);
                twibber = LitePal.find(Twibber.class,twibber.getId());
                if(likeRelations.isEmpty()){
                    LikeRelation likeRelation = new LikeRelation();
                    likeRelation.setTwibberId(uid);
                    likeRelation.setContenterId(twibber.getId());
                    likeRelation.save();
                    tvLike.setText("赞 "+(twibber.getLikeCount()+1));
                    Twibber t = new Twibber();
                    t.setLikeCount(twibber.getLikeCount()+1);
                    t.update(twibber.getId());
                    btnLike.setImageResource(R.drawable.ic_action_liked);
                }else{
                    LitePal.deleteAll(LikeRelation.class,"twibberId = ? and contenterId - ?",""+uid,""+twibber.getId());
                    tvLike.setText("赞 "+(twibber.getLikeCount()-1));
                    Twibber t = new Twibber();
                    t.setLikeCount(twibber.getLikeCount()-1);
                    t.update(twibber.getId());
                    btnLike.setImageResource(R.drawable.ic_action_like);
                }
            }
        });

    }

    private void init() {
        TwibberUserName = findViewById(R.id.nameText_twibber);
        rvComment = findViewById(R.id.twibber_recycler);
        publishDate = findViewById(R.id.timeText_twibber);
        TwibberContent = findViewById(R.id.twibberContent_twibber);
        btnLike = findViewById(R.id.btn_tb_like);
        tvComment = findViewById(R.id.tv_twibber_comment);
        tvLike = findViewById(R.id.tv_twibber_like);
        ibComment=findViewById(R.id.btn_tb_comment);
        avator=findViewById(R.id.icAvatar_twibber);
        tvName=findViewById(R.id.nameText_twibber);


        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
        uid = sp.getInt("id", -1);

        twibber = (Twibber) getIntent().getSerializableExtra("twibber");
        twibber=LitePal.find(Twibber.class,twibber.getId());

        tvComment.setText("评论 " + twibber.getCommentCount());
        tvLike.setText("赞 " + twibber.getLikeCount());

        List<LikeRelation> likeRelations = LitePal.where("twibberId = ? and contenterId = ?", "" + uid, "" + twibber.getId()).find(LikeRelation.class);
        if (!likeRelations.isEmpty())
            btnLike.setImageResource(R.drawable.ic_action_liked);

        TwibberUserName.setText(LitePal.find(User.class, twibber.getPublisherID()).getName());
        publishDate.setText(HomeFragment.getThisTime(twibber.getDate()));
        TwibberContent.setText(twibber.getContent());


        List<Comment> comments = LitePal.where("twibberId = ?", "" + twibber.getId()).find(Comment.class);

        data = new ArrayList<>();
        data.addAll(comments);

        adapter = new CommentAdapter<Comment>(data) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.comment_item;
            }

            @Override
            public void convert(final CommentAdapter.VH holder, final Comment data, int position) {
                holder.setText(R.id.nameText_comment_item, LitePal.find(User.class, data.getCommenterId()).getName());
                holder.setText(R.id.twibber_comment_item, data.getComment());
            }
        };

        rvComment.setLayoutManager(new LinearLayoutManager(this));
        rvComment.setAdapter(adapter);
        rvComment.addItemDecoration(new LinearItemDecoration(this, LinearLayoutManager.VERTICAL));


    }

}
