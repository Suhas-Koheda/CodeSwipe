package com.codeswipe.codeswipe.Auth

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.codeswipe.codeswipe.R


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
                Toast.makeText(this, "Logging you in!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
