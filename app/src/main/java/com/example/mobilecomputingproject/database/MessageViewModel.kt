package com.example.mobilecomputingproject.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MessageViewModel(application: Application) : AndroidViewModel(application) {
    private val messageDao = AppDatabase.getDatabase(application).messageDao()
    val messages: Flow<List<Message>> = messageDao.getAllMessages() // 🔄 Observe messages

    fun addMessage(text: String) {
        val message = Message(text = text)
        viewModelScope.launch {
            messageDao.insertMessage(message) // 🌱 Insert into DB asynchronously
        }
    }
}