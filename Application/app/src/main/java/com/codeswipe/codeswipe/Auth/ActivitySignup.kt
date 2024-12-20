package com.codeswipe.codeswipe.Auth

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.codeswipe.codeswipe.R
import com.codeswipe.codeswipe.db.DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivitySignup : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)

        val usernameEditText = findViewById<EditText>(R.id.editText_username)
        val passwordEditText = findViewById<EditText>(R.id.editText_password)
        val techStackEditText = findViewById<EditText>(R.id.editText_techStack)
        val favProjectEditText = findViewById<EditText>(R.id.editText_favProject)
        val snippetEditText = findViewById<EditText>(R.id.editText_snippet)
        val submitButton = findViewById<Button>(R.id.btn_submit)

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

            // Run signup logic in background thread
            lifecycleScope.launch {
                val database = DB()
                val isSignupSuccessful = withContext(Dispatchers.IO) {
                    database.signup(username, password, techStack, favProjects, snippet)
                }

                if (isSignupSuccessful) {
                    Toast.makeText(this@ActivitySignup, "Sign up successful!", Toast.LENGTH_SHORT).show()
                    // Navigate to another screen or finish activity
                } else {
                    Toast.makeText(this@ActivitySignup, "Sign up failed. Try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
