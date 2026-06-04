package com.example.squaresocialplatform;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.squaresocialplatform.databinding.ProfilelayoutBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;


public class ProfileFeedActivity extends ComponentActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    ArrayList<ProfileFeedItem> listItem = new ArrayList<>();
    SharedPreferences prefs;
    ProfilelayoutBinding binding;
    ActivityResultLauncher<String> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    String fileLocation = saveImageToInternalStorage(uri);

                    db.updateProfilePicture(prefs.getInt("userId", -1), fileLocation);
                    loadScaledImage(fileLocation);

                }

    }
    );


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ProfilelayoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        prefs = getSharedPreferences("session", MODE_PRIVATE);
        TextView profileUsername = findViewById(R.id.usernameProfile);
        profileUsername.setText(prefs.getString("username", "NULL"));


//        listItem.add(new ProfileFeedItem("Username", "random bs"));
//        listItem.add(new ProfileFeedItem("Username", "if the shoe fish"));
//        listItem.add(new ProfileFeedItem("Username", "Benjamin using Cure on the final boss of Mystic Quest damages them severely. Specifically Benjamin. What are you doing man"));
//        listItem.add(new ProfileFeedItem("Username", "They should put axolotls in real life"));
//        listItem.add(new ProfileFeedItem("Username", "I tried playing Dark Souls but got stuck for 5 years on Asylum Demon :("));
//        listItem.add(new ProfileFeedItem("Username", "btw we're all in the matrix lol"));
//        listItem.add(new ProfileFeedItem("Username", "I'm going to eat that entire ****ing pie"));
//        listItem.add(new ProfileFeedItem("Username", "hopital"));


        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets topBar = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0,topBar.top,0,0);
            return insets;
        });

        // TODO: Posts from logged in user needs to show up in the profile page. Use DatabaseHelper and SharedPreferences if you can.


        int userId = prefs.getInt("userId", -1);

// Initialize database helper
        DatabaseHelper db = new DatabaseHelper(this);

// Fetch posts from DB
        ArrayList<FeedItem> rawPosts = db.getSpecificUserPosts(userId);

// Convert FeedItem - ProfileFeedItem
        ArrayList<ProfileFeedItem> listItem = new ArrayList<>();
        for (FeedItem item : rawPosts) {
            String username = item.username;   //  FeedItem
            String content = item.post;        //  FeedItem

            listItem.add(new ProfileFeedItem(username, content));
        }

// Create adapter AFTER list is filled
        ProfileFeed pf = new ProfileFeed(listItem);




        binding.usersPosts.setAdapter(pf);
        binding.usersPosts.setLayoutManager(new LinearLayoutManager(this));

        binding.goHome.setOnClickListener(thing -> goToHome());

        binding.userSwitch2.setOnClickListener(thing -> goToLogin());
        binding.profilePicture.setOnClickListener(thing -> galleryLauncher.launch("image/*"));
        String pfpPath = db.getProfilePicture(prefs.getInt("userId", -1));
        if (pfpPath != null) {
            loadScaledImage(pfpPath);
        }

    }

    public void goToHome(){
        Intent intent = new Intent(this, FeedActivity.class);
        startActivity(intent);
    }

    public void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private void loadScaledImage(String path) {
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, bounds);

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = Math.max(bounds.outWidth / 512, bounds.outHeight / 512);
        if (opts.inSampleSize < 1) opts.inSampleSize = 1;

        Bitmap scaled = BitmapFactory.decodeFile(path, opts);
        binding.profilePicture.setImageBitmap(scaled);
    }

    private String saveImageToInternalStorage(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            File file = new File(getFilesDir(), "profile_" + System.currentTimeMillis() + ".jpg");
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }
}
