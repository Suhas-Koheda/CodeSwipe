package com.codeswipe.codeswipe;

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeActivity: ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_dashboard)
        
        val usernameLabel = findViewById<TextView>(R.id.username_value)
        val techStackLabel = findViewById<TextView>(R.id.tech_stack_value)
        val favProjectsLabel = findViewById<TextView>(R.id.fav_projects_value)
        val snippetLabel = findViewById<TextView>(R.id.snippet_value)

        val username=intent.getStringExtra("username")
        val db= Firebase.firestore
        db.collection("UserData")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document.data["username"] == username) {
                        val techStack = document.data["techStack"] as List<String>
                        val favProjects = document.data["favProjects"] as List<String>
                        val snippet = document.data["snippet"] as String

                        usernameLabel.text = username
                        techStackLabel.text = techStack.joinToString(", ")
                        favProjectsLabel.text = favProjects.joinToString("\n")
                        snippetLabel.text = snippet

                    }
                }
            }
    }
}
