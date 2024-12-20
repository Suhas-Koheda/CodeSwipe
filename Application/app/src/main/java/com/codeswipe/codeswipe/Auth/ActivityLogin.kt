package com.codeswipe.codeswipe.Auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.codeswipe.codeswipe.HomeActivity
import com.codeswipe.codeswipe.R
import com.codeswipe.codeswipe.db.DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityLogin : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val username = findViewById<EditText>(R.id.editText1)
        val password = findViewById<EditText>(R.id.editText2)

        findViewById<Button>(R.id.btn_submit).setOnClickListener {
            val un = username.text.toString()
            val ps = password.text.toString()
            if (un.isNotEmpty() && ps.isNotEmpty()) {
                // Run login logic in background thread
                lifecycleScope.launch {
                    val db = DB()
                    val loginSuccessful = withContext(Dispatchers.IO) {
                        db.login(un, ps)
                    }

                    if (loginSuccessful) {
                        val intent = Intent(this@ActivityLogin, HomeActivity::class.java)
                        intent.putExtra("username", un)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@ActivityLogin, "Login failed. Try again.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
