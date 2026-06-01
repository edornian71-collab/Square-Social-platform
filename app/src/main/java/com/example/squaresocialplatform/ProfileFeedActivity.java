package com.example.squaresocialplatform;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.squaresocialplatform.databinding.ProfilelayoutBinding;

import java.util.ArrayList;

public class ProfileFeedActivity extends ComponentActivity {
    ArrayList<ProfileFeedItem> listItem = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProfilelayoutBinding binding = ProfilelayoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        listItem.add(new ProfileFeedItem("Username", "random bs"));
        listItem.add(new ProfileFeedItem("Username", "if the shoe fish"));
        listItem.add(new ProfileFeedItem("Username", "Benjamin using Cure on the final boss of Mystic Quest damages them severely. Specifically Benjamin. What are you doing man"));
        listItem.add(new ProfileFeedItem("Username", "They should put axolotls in real life"));
        listItem.add(new ProfileFeedItem("Username", "I tried playing Dark Souls but got stuck for 5 years on Asylum Demon :("));
        listItem.add(new ProfileFeedItem("Username", "btw we're all in the matrix lol"));
        listItem.add(new ProfileFeedItem("Username", "I'm going to eat that entire ****ing pie"));
        listItem.add(new ProfileFeedItem("Username", "hopital"));

        ProfileFeed pf = new ProfileFeed(listItem);

        binding.usersPosts.setAdapter(pf);
        binding.usersPosts.setLayoutManager(new LinearLayoutManager(this));

        binding.goHome.setOnClickListener(thing -> goToHome());

        binding.userSwitch2.setOnClickListener(thing -> goToLogin());
    }

    public void goToHome(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
