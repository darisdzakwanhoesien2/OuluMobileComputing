package com.example.mobilecomputingproject.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilecomputingproject.database.Message
import com.example.mobilecomputingproject.database.MessageViewModel
import kotlinx.coroutines.flow.collectAsState

@Composable
fun MessageScreen(viewModel: MessageViewModel = viewModel()) {
    val textState = remember { mutableStateOf("") }
    val messages by viewModel.messages.collectAsState(initial = emptyList()) // 🔄 Observe DB changes

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text("Enter message") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (textState.value.isNotBlank()) {
                viewModel.addMessage(textState.value)
                textState.value = "" // 🔄 Clear input field after adding message
            }
        }) {
            Text("Add Message")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(messages) { message ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    elevation = 4.dp
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = message.text)
                        Text(text = "Sent: ${java.text.SimpleDateFormat("HH:mm:ss").format(message.timestamp)}", style = MaterialTheme.typography.caption)
                    }
                }
            }
        }
    }
}
