package com.example.texgram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView tvUsername;
    TextView tvCaption;
    ImageView ivPhoto;
    TextView tvCreatedTime;

    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvUsername = findViewById(R.id.tvUsername);
        tvCaption = findViewById(R.id.tvCaption);
        ivPhoto = findViewById(R.id.ivPhoto);
        tvCreatedTime = findViewById(R.id.tvCreatedTime);

        post = getIntent().getParcelableExtra("post");

        tvUsername.setText(post.getUser().getUsername());
        tvCaption.setText(post.getDescription());
        tvCreatedTime.setText(Post.getRelativeTimeAgo(post.getCreatedAt()));

        Glide.with(this).load(post.getImage().getUrl()).into(ivPhoto);
    }
}
