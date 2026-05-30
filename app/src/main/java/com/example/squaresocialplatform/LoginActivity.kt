package com.example.squaresocialplatform

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.databasehelper.DatabaseHelper

class LoginActivity : AppCompatActivity() {

    // UI elements + DB helper
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginlayout)

        // Init database helper
        db = DatabaseHelper(this)

        // Hook up UI elements
        etEmail    = findViewById(R.id.email)
        etPassword = findViewById(R.id.password)
        btnLogin   = findViewById(R.id.loginBtn)

        // When user taps "Login"
        btnLogin.setOnClickListener {

            // Grab text from inputs
            val email    = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Basic empty-field check
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Ask DB if email + password match a user
            val userName = db.loginUser(email, password)

            if (userName != null) {
                // Login success — send user to main screen
                Toast.makeText(this, "Welcome back, $userName!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish() // Close login screen so user can't go back
            } else {
                // Wrong email or password
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
