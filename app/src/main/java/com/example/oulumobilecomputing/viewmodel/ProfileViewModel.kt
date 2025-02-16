package com.example.mainproject.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mainproject.database.AppDatabase
import com.example.mainproject.database.UserEntity
import com.example.mainproject.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(context: Context) : ViewModel() {
    private val repository: UserRepository

    init {
        val database = AppDatabase.getDatabase(context)
        repository = UserRepository(database, context)
    }

    fun saveUserData(name: String, imageUri: Uri?, onComplete: (String?) -> Unit) {
        viewModelScope.launch {
            val savedImagePath = repository.saveUserData(name, imageUri)
            onComplete(savedImagePath)
        }
    }

    fun getUserData(onComplete: (UserEntity?) -> Unit) {
        viewModelScope.launch {
            val userData = repository.getUserData()
            onComplete(userData)
        }
    }
}
