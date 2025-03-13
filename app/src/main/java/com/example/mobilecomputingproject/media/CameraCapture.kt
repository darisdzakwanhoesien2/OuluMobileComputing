package com.example.mobilecomputingproject.media

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CameraCapture() {
    val context = LocalContext.current
    val imageDirectory = File(context.getExternalFilesDir(null), "CapturedImages")

    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var savedImages by remember { mutableStateOf(loadSavedImages(imageDirectory)) }

    // Camera launcher
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let {
            imageBitmap = it
            val savedUri = saveImageToStorage(it, imageDirectory, context)
            savedImages = loadSavedImages(imageDirectory)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Camera Capture", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))

        // Take Picture Button
        Button(onClick = { launcher.launch() }, modifier = Modifier.fillMaxWidth()) {
            Text("Take Picture")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display latest captured image
        imageBitmap?.let {
            Image(bitmap = it.asImageBitmap(), contentDescription = "Captured Image", modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Show Gallery of Taken Pictures
        Text("Saved Pictures", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))

        LazyColumn {
            items(savedImages) { uri ->
                ImageCard(uri)
            }
        }
    }
}

// Function to save image to storage
fun saveImageToStorage(bitmap: Bitmap, directory: File, context: Context): Uri? {
    if (!directory.exists()) {
        directory.mkdirs()
    }
    
    val fileName = "IMG_${SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())}.jpg"
    val file = File(directory, fileName)

    return try {
        FileOutputStream(file).use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        }
        Uri.fromFile(file)
    } catch (e: Exception) {
        Toast.makeText(context, "Error saving image", Toast.LENGTH_SHORT).show()
        null
    }
}

// Function to load saved images from storage
fun loadSavedImages(directory: File): List<Uri> {
    return directory.listFiles()?.map { Uri.fromFile(it) } ?: emptyList()
}

// Composable to display an image from a URI
@Composable
fun ImageCard(imageUri: Uri) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberAsyncImagePainter(imageUri),
                contentDescription = "Saved Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = imageUri.lastPathSegment ?: "Unknown", fontSize = 12.sp)
        }
    }
}