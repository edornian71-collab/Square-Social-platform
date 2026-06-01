package com.example.squaresocialplatform

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginlayout)

        db = DatabaseHelper(this)

        etEmail = findViewById(R.id.email)
        etPassword = findViewById(R.id.password)
        btnLogin = findViewById(R.id.loginBtn)
        btnRegister = findViewById(R.id.regBtn)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val loggedInUser = db.loginUser(email, password)

            if (loggedInUser != null) {
                val prefs = getSharedPreferences("session", MODE_PRIVATE)
                prefs.edit {
                    putString("username", loggedInUser.username)
                    putString("email",loggedInUser.email)
                    putInt("userId", loggedInUser.id)
                }
                Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, CreatePostActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
