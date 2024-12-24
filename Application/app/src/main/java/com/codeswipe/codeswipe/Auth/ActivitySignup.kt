package com.codeswipe.codeswipe.Auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.codeswipe.codeswipe.HomeActivity
import com.codeswipe.codeswipe.R
import com.google.firebase.FirebaseApp

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActivitySignup : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)
        FirebaseApp.initializeApp(this)
        val usernameEditText = findViewById<EditText>(R.id.editText_username)
        val passwordEditText = findViewById<EditText>(R.id.editText_password)
        val techStackEditText = findViewById<EditText>(R.id.editText_techStack)
        val favProjectEditText = findViewById<EditText>(R.id.editText_favProject)
        val snippetEditText = findViewById<EditText>(R.id.editText_snippet)
        val submitButton = findViewById<Button>(R.id.btn_submit)

        val db= Firebase.firestore

        submitButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val techStackInput = techStackEditText.text.toString().trim()
            val favProjectInput = favProjectEditText.text.toString().trim()
            val snippet = snippetEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty() || techStackInput.isEmpty() || favProjectInput.isEmpty() || snippet.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val techStack = techStackInput.split(",").map { it.trim() }
            val favProjects = favProjectInput.split(",").map { it.trim() }

            val userData = hashMapOf(
                "username" to username,
                "password" to password,
                "techStack" to techStack,
                "favProjects" to favProjects,
                "snippet" to snippet
            )

            db.collection("UserData")
                .document(username)
                .set(userData)
                .addOnSuccessListener {
                    Toast.makeText(this, "User $username added to database successfully", Toast.LENGTH_SHORT).show()
                    val intent=Intent(this,HomeActivity::class.java)
                    intent.putExtra("username",username)
                    startActivity(intent)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
