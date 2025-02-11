package com.example.oulumobilecomputing.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,  // Ensure this is marked as @PrimaryKey
    val username: String,
    val imagePath: String
)

