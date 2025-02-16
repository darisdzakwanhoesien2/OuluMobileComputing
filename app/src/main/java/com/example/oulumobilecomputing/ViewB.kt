package com.example.oulumobilecomputing

import android.net.Uri
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewB(navController: NavHostController, context: Context) {
    val userPreferences = remember { UserPreferences(context) }
    val coroutineScope = rememberCoroutineScope()

    var username by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    LaunchedEffect(Unit) {
        userPreferences.usernameFlow.collect { savedUsername ->
            savedUsername?.let { username = it }
        }
        ImageStorageHelper.getSavedImagePath(context)?.let { storedImagePath ->
            imageUri = Uri.parse(storedImagePath)
        }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            imageUri = uri
            val savedImagePath = ImageStorageHelper.saveImage(context, uri)
            imageUri = Uri.parse(savedImagePath)
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Set Profile") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)
        ) {
            Text("Enter Username:")
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Username") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { imagePickerLauncher.launch("image/*") }) {
                Text("Pick Profile Picture")
            }

            Spacer(modifier = Modifier.height(16.dp))

            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Selected Image",
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    coroutineScope.launch { userPreferences.saveUsername(username) }
                    navController.navigate("ViewC")
                }
            ) {
                Text("Go to Chat")
            }
        }
    }
}
