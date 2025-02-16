package com.example.oulumobilecomputing

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewC(navController: NavHostController, context: Context) {
    val userPreferences = remember { UserPreferences(context) }
    val username = runBlocking { userPreferences.usernameFlow.first() ?: "Default User" }
    val imageUri = ImageStorageHelper.getSavedImagePath(context)?.let { Uri.parse(it) }

    var messageText by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<Pair<String, Boolean>>() } // (Message, isUser)

    Scaffold(
        topBar = { TopAppBar(title = { Text("Chat with $username") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // ✅ Chat Messages List
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                reverseLayout = true
            ) {
                items(messages.reversed()) { (message, isUser) ->
                    ChatBubble(message = message, isUser = isUser, imageUri = imageUri)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ✅ Message Input Field
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .height(48.dp)
                        .fillMaxWidth(),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                            if (messageText.isEmpty()) Text("Type a message...", color = Color.Gray)
                            innerTextField()
                        }
                    }
                )

                Button(
                    onClick = {
                        if (messageText.isNotBlank()) {
                            messages.add(Pair(messageText, true)) // User message
                            messages.add(Pair("This is a bot response!", false)) // Bot response
                            messageText = ""
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Send")
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: String, isUser: Boolean, imageUri: Uri?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        if (!isUser) {
            // ✅ Profile Picture as Avatar (Bot Messages)
            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // ✅ Chat Bubble
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = if (isUser) Color.Blue else Color.Gray,
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = message,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }

        if (isUser) {
            Spacer(modifier = Modifier.width(8.dp))

            // ✅ Profile Picture as Avatar (User Messages)
            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}