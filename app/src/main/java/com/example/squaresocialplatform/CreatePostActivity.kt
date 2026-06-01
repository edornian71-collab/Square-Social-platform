package com.example.squaresocialplatform

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class CreatePostActivity : AppCompatActivity() {
    private lateinit var textBox : EditText
    private lateinit var profilePicture: ImageView
    private lateinit var exitButton : Button
    private lateinit var username : TextView
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.createpostlayout)

        textBox = findViewById(R.id.editPostField)
        profilePicture = findViewById(R.id.profilePicture)
        username = findViewById(R.id.usernameText)
        exitButton = findViewById(R.id.exitButton)
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

        username.text = savedUsername

        exitButton.setOnClickListener {
            finish()
        }

    }
}