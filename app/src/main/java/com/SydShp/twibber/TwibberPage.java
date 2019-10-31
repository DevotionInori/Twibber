package com.SydShp.twibber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.SydShp.twibber.model.Comment;
import com.SydShp.twibber.model.LikeRelation;
import com.SydShp.twibber.model.Twibber;
import com.SydShp.twibber.model.User;

import org.litepal.LitePal;

import java.util.List;

public class TwibberPage extends AppCompatActivity {

    private Toolbar mtoolbar;
    private RecyclerView rvComment;
    private TextView TwibberUserName;
    private TextView publishDate;
    private TextView TwibberContent;
    private ImageButton btnLike;
    private int uid;
    private Twibber twibber;
    private TextView tvComment;
    private TextView tvLike;
    private PopupWindow popupWindow;
    private View contentView;
    private CommentAdapter adapter;

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

        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
        uid = sp.getInt("id", -1);

        twibber = (Twibber) getIntent().getSerializableExtra("twibber");

        tvComment.setText("评论 " + twibber.getCommentCount());
        tvLike.setText("赞 " + twibber.getLikeCount());

        List<LikeRelation> likeRelations = LitePal.where("twibberId = ? and contenterId = ?", "" + uid, "" + twibber.getId()).find(LikeRelation.class);
        if (!likeRelations.isEmpty())
            btnLike.setImageResource(R.drawable.ic_action_liked);

        TwibberUserName.setText(LitePal.find(User.class, twibber.getPublisherID()).getName());
        publishDate.setText(HomeFragment.getThisTime(twibber.getDate()));
        TwibberContent.setText(twibber.getContent());


        List<Comment> comments = LitePal.where("twibberId = ?", "" + twibber.getId()).find(Comment.class);

        adapter = new CommentAdapter<Comment>(comments) {
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
