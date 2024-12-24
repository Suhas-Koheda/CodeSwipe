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
//import com.codeswipe.codeswipe.db.DB
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityLogin : ComponentActivity() {

    val db=Firebase.firestore;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val username = findViewById<EditText>(R.id.editText1)
        val password = findViewById<EditText>(R.id.editText2)

        findViewById<Button>(R.id.btn_submit).setOnClickListener {
            val un = username.text.toString()
            val ps = password.text.toString()
            if (un.isNotEmpty() && ps.isNotEmpty()) {
                db.collection("UserData")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            if (document.data["username"] == un && document.data["password"] == ps) {
                                val intent=Intent(this,HomeActivity::class.java)
                                intent.putExtra("username",un)
                                Toast.makeText(this, "User $un logged in successfully", Toast.LENGTH_SHORT).show()
                                startActivity(intent)
                            }
                            else{
                                Toast.makeText(this, "Invalid Credentials for user $un", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

            }
        }
    }
}
