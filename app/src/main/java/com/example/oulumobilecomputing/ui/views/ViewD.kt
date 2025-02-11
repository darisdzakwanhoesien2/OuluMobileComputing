package com.example.oulumobilecomputing.ui.views

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import java.io.File
import com.example.oulumobilecomputing.data.UserProfile
import com.example.oulumobilecomputing.OuluApplication

// Helper function to copy the image from the provided Uri to internal storage.
fun copyImageToInternalStorage(context: Context, imageUri: Uri): String? {
    val filename = "profile_image_${System.currentTimeMillis()}.jpg"
    val file = File(context.filesDir, filename)
    return try {
        context.contentResolver.openInputStream(imageUri)?.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        file.absolutePath
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewD(navController: NavHostController, context: Context = LocalContext.current) {
    var username by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var copiedImagePath by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri
        uri?.let {
            copiedImagePath = copyImageToInternalStorage(context, it)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("View D - Profile Picture") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("This is View D", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Enter Username") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { imagePickerLauncher.launch("image/*") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pick Profile Picture")
            }

            Spacer(modifier = Modifier.height(16.dp))

            copiedImagePath?.let { path ->
                Image(
                    painter = rememberAsyncImagePainter(File(path)),
                    contentDescription = "Selected Image",
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    copiedImagePath?.let { imagePath ->
                        coroutineScope.launch {
                            val profile = UserProfile(username = username, imagePath = imagePath)
                            OuluApplication.database.userProfileDao().insertUserProfile(profile)
                        }
                    }
                    navController.navigate("view_e")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to View E")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.popBackStack("view_c", inclusive = false) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back to View C")
            }
        }
    }
}