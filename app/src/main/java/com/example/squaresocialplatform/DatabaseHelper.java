package com.example.squaresocialplatform;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "squaresocial.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Your CREATE TABLE statement goes here
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
        // Drop and recreate for now
        db.execSQL("DROP TABLE IF EXISTS Posts");
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }

}