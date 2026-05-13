package com.example.squaresocialplatform

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.squaresocialplatform.ui.theme.SqaureSocialPlatformTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginlayout)

        // input fields and login buttton
        val emailInput = findViewById<EditText>(R.id.email)
        val passInput = findViewById<EditText>(R.id.password)

        val loginBtn = findViewById<Button>(R.id.loginBtn)

        loginBtn.setOnClickListener {

            val email = emailInput.text.toString().trim()
            val password = passInput.text.toString().trim()

            //check if user registered
            // need to grab info from registration page

            //username will come from registration page
            //if(email == "ethan@gmail.com" && password == "password" ){
               // Toast.makeText(this,"Hello $username").show()


                //navigate to home page after login
                //val intent = Intent(this, HomeActivity::class.java)
                //startActivity(intent)
               // finish()

            }
        }





    }
