package com.codeswipe.codeswipe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.codeswipe.codeswipe.Auth.ActivityLogin
import com.codeswipe.codeswipe.Auth.ActivitySignup
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)
        FirebaseApp.initializeApp(this)
        val loginButton = findViewById<Button>(R.id.btn_login)
        loginButton.setOnClickListener {
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn_signup).setOnClickListener{
            startActivity(Intent(this,ActivitySignup::class.java))
        }
    }
}