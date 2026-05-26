package com.example.squaresocialplatform;

import android.os.Bundle;
import android.view.View;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.squaresocialplatform.databinding.FeedLayoutBinding;
import com.example.squaresocialplatform.databinding.FeedLayoutBinding;

import java.util.ArrayList;

public class FeedActivity extends ComponentActivity {

    ArrayList<item> listItem = new ArrayList<>();




    public void whenGo() {


        FeedLayoutBinding binding = FeedLayoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        listItem.add(new item("241", "adasd"));
        listItem.add(new item("3523", "sdfata"));
        listItem.add(new item("588", "gsdfkgjae"));
        listItem.add(new item("558", "qowefhiwaeojfuaowebh"));
        listItem.add(new item("32498", "wuerqijehjsf[p"));
        listItem.add(new item("76458", "lweyf9qyrbw"));
        listItem.add(new item("741", "wuiehfjkbnwae"));
        listItem.add(new item("9654", "awehfiuqbwefjkb"));
        listItem.add(new item("3248", "aouwefhiubhwefa"));
        listItem.add(new item("879847", "ierhghenrsan"));
        listItem.add(new item("484", "awuefbubwabue"));
        listItem.add(new item("888847", "bjwbefiubiwubef"));
        listItem.add(new item("45654", "biwavbecbvifawb"));

        Feed feed = new Feed(listItem);

        binding.scroll.setAdapter(feed);

        binding.scroll.setLayoutManager(new LinearLayoutManager(this));


    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
