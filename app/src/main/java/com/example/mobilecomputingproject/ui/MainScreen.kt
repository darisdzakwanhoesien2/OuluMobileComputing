package com.example.mobilecomputingproject.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilecomputingproject.database.MessageViewModel

@Composable
fun MessageScreen(viewModel: MessageViewModel) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text("Enter message") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.addMessage(textState.value.text) }) {
            Text("Add Message")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(viewModel.messages) { message ->
                Text(text = message.text)
            }
        }
    }
}
