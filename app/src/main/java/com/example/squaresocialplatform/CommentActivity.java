package com.example.squaresocialplatform;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.squaresocialplatform.databinding.CommentLayoutBinding;

public class CommentActivity extends ComponentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommentLayoutBinding binding = CommentLayoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        String receivedUser = getIntent().getStringExtra("USER_NAME");

        Comment comment = new Comment(null);



        binding.profile.setOnClickListener(thing -> goToProfile());

        // binding.userSwitch.setOnClickListener(thing -> goToLogin());

        binding.createPostButton2.setOnClickListener(thing -> goToNewPost());

        binding.backButton.setOnClickListener(thing -> goToFeed());

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets topBar = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0,topBar.top,0,0);
            return insets;
        });

    }

    public void goToProfile(){
        Intent intent = new Intent(this, ProfileFeedActivity.class);
        startActivity(intent);
    }

    public void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToNewPost(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToFeed(){
        Intent intent = new Intent(this, FeedActivity.class);
        startActivity(intent);
    }
}
