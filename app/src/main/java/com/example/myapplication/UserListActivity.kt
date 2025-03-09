package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Create mock user data
        val users = listOf(
            User("Alice", "alice@example.com", "Enthusiastic Android Developer"),
            User("Bob", "bob@example.com", "Kotlin Expert & Tech Enthusiast"),
            User("Charlie", "charlie@example.com", "UI/UX Designer with a creative edge")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UserAdapter(users)

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            sharedPreferences.edit().remove("loggedInUser").apply() // Remove session
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
