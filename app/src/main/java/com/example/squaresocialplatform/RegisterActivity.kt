package com.example.squaresocialplatform

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.databasehelper.DatabaseHelper

class RegisterActivity : AppCompatActivity() {

    // Inputs + button + DB helper
    // (lateinit because we hook them up in onCreate)
    private lateinit var etUserName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrationlayout)

        // Init database helper
        db = DatabaseHelper(this)

        // Grab UI elements from layout
        etUserName = findViewById(R.id.userName)
        etEmail = findViewById(R.id.newEmail)
        etPassword = findViewById(R.id.newPass)
        btnRegister = findViewById(R.id.regBtn)

        // When user taps "Register"
        btnRegister.setOnClickListener {

            // Pull text from inputs
            val username = etUserName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Basic empty check — no blank fields allowed
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Quick email format check
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simple password rule — at least 6 chars
            if (password.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            // Check if email already exists in DB
            if (db.emailExists(email)) {
                Toast.makeText(this, "Email is already registered", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Try to insert user into DB
            val success = db.registerUser(username, email, password)

            if (success) {
                // Registration worked — send user to login screen
                Toast.makeText(this, "Account created! Please log in.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish() // Close this screen so user can't go back
            } else {
                // Something went wrong inserting into DB
                Toast.makeText(this, "Registration failed. Try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
