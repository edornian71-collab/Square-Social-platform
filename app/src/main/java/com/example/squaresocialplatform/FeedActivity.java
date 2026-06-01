package com.example.squaresocialplatform;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.database.sqlite.SQLiteOpenHelper;



import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.squaresocialplatform.databinding.FeedLayoutBinding;

import java.util.ArrayList;

public class FeedActivity extends ComponentActivity {

    DatabaseHelper db = new DatabaseHelper(this);

    ArrayList<FeedItem> listItem = db.fetchItem();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        FeedLayoutBinding binding = FeedLayoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
//        listItem.add(new FeedItem("241", "adasd"));
//        listItem.add(new FeedItem("3523", "sdfata"));
//        listItem.add(new FeedItem("588", "gsdfkgjae"));
//        listItem.add(new FeedItem("558", "qowefhiwaeojfuaowebh"));
//        listItem.add(new FeedItem("32498", "wuerqijehjsf[p"));
//        listItem.add(new FeedItem("76458", "lweyf9qyrbw"));
//        listItem.add(new FeedItem("741", "wuiehfjkbnwae"));
//        listItem.add(new FeedItem("9654", "awehfiuqbwefjkb"));
//        listItem.add(new FeedItem("3248", "aouwefhiubhwefa"));
//        listItem.add(new FeedItem("879847", "ierhghenrsan"));
//        listItem.add(new FeedItem("484", "awuefbubwabue"));
//        listItem.add(new FeedItem("888847", "bjwbefiubiwubef"));
//        listItem.add(new FeedItem("45654", "biwavbecbvifawb"));

        Feed feed = new Feed(listItem);

        binding.scroll.setAdapter(feed);

        binding.scroll.setLayoutManager(new LinearLayoutManager(this));

        binding.profile.setOnClickListener(thing -> goToProfile());

        binding.userSwitch.setOnClickListener(thing -> goToLogin());

        binding.createPostButton.setOnClickListener(thing -> goToNewPost());

        // Databasehelper db = new Database


    }

    public void goToProfile(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToNewPost(){
        Intent intent = new Intent(this, CreatePostActivity.class);
        startActivity(intent);
    }
}
