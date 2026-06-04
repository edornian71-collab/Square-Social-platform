package com.example.squaresocialplatform;

import android.net.Uri;

public class FeedItem {
    String pfpFeed;
    String username;
    String post;
    boolean isExpanded;

    public FeedItem(String username, String content,String pfpFeed) {
        this.username = username;
        this.post = content;
        this.pfpFeed = pfpFeed;
    }

}
