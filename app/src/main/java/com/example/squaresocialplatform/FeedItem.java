package com.example.squaresocialplatform;

public class FeedItem {
    String username;
    String post;
    boolean isExpanded;

    public FeedItem(String username, String content) {
        this.username = username;
        this.post = content;
    }

}
