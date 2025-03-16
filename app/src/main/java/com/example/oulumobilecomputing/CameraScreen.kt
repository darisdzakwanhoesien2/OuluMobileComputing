package com.example.oulumobilecomputing

import android.net.Uri
import android.os.Environment
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
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraScreen(navController: NavHostController) {
    val context = LocalContext.current
    var photoUri by remember { mutableStateOf<Uri?>(null) }

    // Prepare file for storing the captured image
    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "captured_photo.jpg")
    val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)

    // Register the camera launcher
    val takePictureLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            photoUri = uri
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Take a Picture") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { takePictureLauncher.launch(uri) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Capture Photo")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Show captured image
            photoUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Captured Image",
                    modifier = Modifier.fillMaxWidth().height(300.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("gallery") }, // Navigate to Image Gallery
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to Gallery")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("main") }, // Navigate back to Home
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back to Main Screen")
            }
        }
    }
}
