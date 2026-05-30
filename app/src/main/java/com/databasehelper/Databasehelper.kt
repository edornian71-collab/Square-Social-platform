package com.databasehelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

// Simple SQLite helper for storing user accounts.
// Nothing fancy — just name, email, password.
class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // DB file name
        private const val DATABASE_NAME = "UserDB"

        // Bump this if you change the schema
        private const val DATABASE_VERSION = 1

        // Table + column names (keeping it simple)
        private const val TABLE_USERS = "users"
        private const val COL_ID = "id"
        private const val COL_NAME = "name"
        private const val COL_EMAIL = "email"
        private const val COL_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create the users table — auto‑increment ID, unique email
        val createTable = """
            CREATE TABLE $TABLE_USERS (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NAME TEXT,
                $COL_EMAIL TEXT UNIQUE,
                $COL_PASSWORD TEXT
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Easiest upgrade strategy: drop and recreate.
        // (Fine for small apps — not for production.)
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    // REGISTER USER
    // Inserts a new user. Returns true if it worked.
    // If email already exists, insert() returns -1.
    fun registerUser(name: String, email: String, password: String): Boolean {
        val db = writableDatabase

        val values = ContentValues().apply {
            put(COL_NAME, name)
            put(COL_EMAIL, email)
            put(COL_PASSWORD, password) // Reminder: hash passwords in real apps
        }

        val result = db.insert(TABLE_USERS, null, values)

        db.close()
        return result != -1L
    }

    // LOGIN USE
    // Checks if email + password match a row.
    // If yes → return the user's name.
    // If no → return null.
    fun loginUser(email: String, password: String): String? {
        val db = readableDatabase

        val cursor = db.query(
            TABLE_USERS,
            arrayOf(COL_NAME),                     // Only need the name back
            "$COL_EMAIL = ? AND $COL_PASSWORD = ?", // WHERE email AND password
            arrayOf(email, password),
            null, null, null
        )

        // If a row exists, grab the name
        val name = if (cursor.moveToFirst()) cursor.getString(0) else null

        cursor.close()
        db.close()
        return name
    }


    // CHECK IF EMAIL EXISTS
    // Quick check to see if an email is already registered.
    // Just returns true/false — no need to fetch full user data.
    fun emailExists(email: String): Boolean {
        val db = readableDatabase

        val cursor = db.query(
            TABLE_USERS,
            arrayOf(COL_ID), // Only need ID to confirm existence
            "$COL_EMAIL = ?",
            arrayOf(email),
            null, null, null
        )

        val exists = cursor.count > 0

        cursor.close()
        db.close()
        return exists
    }
}


