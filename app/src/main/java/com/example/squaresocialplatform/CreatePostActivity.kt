package com.example.squaresocialplatform

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class CreatePostActivity : AppCompatActivity() {
    private lateinit var db : DatabaseHelper
    private lateinit var textBox : EditText
    private lateinit var profilePicture: ImageView
    private lateinit var exitButton : Button
    private lateinit var username : TextView
    private lateinit var postButton : Button
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.createpostlayout)
        db = DatabaseHelper(this)

        textBox = findViewById(R.id.editPostField)
        profilePicture = findViewById(R.id.profilePicture)
        username = findViewById(R.id.usernameText)
        exitButton = findViewById(R.id.exitButton)
        postButton = findViewById(R.id.postButton)
        val prefs = getSharedPreferences("session", MODE_PRIVATE)
        val savedUsername = prefs.getString("username", null)
        val layout = findViewById<ConstraintLayout>(R.id.createPostLayout)
        layout.setOnTouchListener { v, event ->
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            currentFocus?.clearFocus()
            v.performClick()
            false
        }


        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.createPostLayout),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val topBar = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(0, topBar.top, 0, 0)
                insets
            })

        username.text = savedUsername

        exitButton.setOnClickListener {
            finish()
        }

        postButton.setOnClickListener {
            val userId = prefs.getInt("userId", -1)
            val postContent = textBox.text.toString()
            if (postContent.isEmpty()) {
                Toast.makeText(this, "Text box is empty.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val isPostCreationSuccessful = db.createPost(userId, postContent)
            if (!isPostCreationSuccessful) {
                Toast.makeText(this, "Creating post was unsuccessful.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else {
                Toast.makeText(this, "Posted!", Toast.LENGTH_SHORT).show()
                finish()
            }

        }

    }
}