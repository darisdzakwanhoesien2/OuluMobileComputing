package com.example.mobilecomputingproject.database

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MessageViewModel(context: Context) : ViewModel() {
    private val db = AppDatabase.getDatabase(context)
    val messages = mutableListOf<Message>()

    init {
        loadMessages()
    }

    fun loadMessages() {
        messages.clear()
        messages.addAll(db.messageDao().getAll())
    }

    fun addMessage(text: String) {
        val message = Message(text = text)
        viewModelScope.launch {
            db.messageDao().insert(message)
            loadMessages()
        }
    }
}