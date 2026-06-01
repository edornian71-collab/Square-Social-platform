package com.example.squaresocialplatform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Handles creation and access to the local SQLite database.
 * Provides methods for user registration, login, and post creation.
 *
 * <p>Usage: instantiate with a {@link android.content.Context}, then call the relevant method.</p>
 *
 * <pre>
 *     DatabaseHelper db = new DatabaseHelper(context);
 *     User user = db.loginUser(email, password);
 * </pre>
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "squaresocial.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Users (" +
                "UserId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT, " +
                "Password TEXT, " +
                "Email TEXT" +
                ")");
        db.execSQL("CREATE TABLE Posts (" +
                "PostId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "UserId INTEGER, " +
                "Content TEXT, " +
                "FOREIGN KEY (UserId) REFERENCES Users(UserId)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Posts");
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }
    /**
     * Registers a new user in the database.
     *
     * @param username the user's display name
     * @param password the user's password
     * @param email    the user's email address
     * @return true if registration succeeded, false otherwise
     */
    public boolean registerUser(String username, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (!ifEmailAlreadyExists(email)) {
            values.put("Username", username);
            values.put("Password", password);
            values.put("Email", email);
            long attempt = db.insert("Users", null, values);
            boolean ifAttemptSuccess = attempt != -1;
            return ifAttemptSuccess;
        } else {
            return false;
        }
    }
    /**
     * Attempts to log in a user with the given credentials.
     *
     * @param email    the user's email address
     * @param password the user's password
     * @return a {@link User} object with id, username, and email if credentials matched, null otherwise
     */
    public User loginUser(String email, String password) {
        User currentUser;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE Email=? AND Password=?", new String[]{email, password});
        if(cursor.moveToFirst()) {
            currentUser = new User();
            currentUser.id = cursor.getInt(cursor.getColumnIndexOrThrow("UserId"));
            currentUser.email = cursor.getString(cursor.getColumnIndexOrThrow("Email"));
            currentUser.username = cursor.getString(cursor.getColumnIndexOrThrow("Username"));
        } else {

            return null;
        }
        return currentUser;
    }


    /**
     * Checks whether an email address is already registered.
     *
     * @param email the email address to check
     * @return true if the email exists in the database, false otherwise
     */
    public boolean ifEmailAlreadyExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE Email=?", new String[]{email});
        boolean found = cursor.moveToFirst();
        return found;
    }

    /**
     * Saves a new post to the database.
     *
     * @param userId   the ID of the user creating the post
     * @param postText the text content of the post
     * @return true if the post was saved successfully, false otherwise
     */
    public boolean createPost(int userId, String postText) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues postValues = new ContentValues();

        postValues.put("UserId", userId);
        postValues.put("Content", postText);
        long postAttempt = db.insert("Posts", null, postValues);
        return postAttempt != -1;
    }
    public ArrayList<FeedItem> getAllPosts() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<FeedItem> feedItems = new ArrayList<FeedItem>();
        Cursor cursor = db.rawQuery("SELECT Users.Username, Posts.Content FROM Posts JOIN Users ON Posts.UserId = Users.UserId", null);
        while (cursor.moveToNext()) {
            feedItems.add(new FeedItem(cursor.getString(cursor.getColumnIndexOrThrow("Username")), cursor.getString(cursor.getColumnIndexOrThrow("Content"))));
        }

        return feedItems;
    }

}