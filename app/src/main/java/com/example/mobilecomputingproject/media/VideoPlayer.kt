package com.example.mobilecomputingproject.media

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun VideoPlayer() {
    val context = LocalContext.current
    var videoUri by remember { mutableStateOf<Uri?>(null) }

    val videoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                videoUri = uri
            } else {
                Toast.makeText(context, "No video selected", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Column(modifier = Modifier.padding(16.dp)) {
        // 📂 Pick Video Button
        Button(onClick = { videoPicker.launch("video/*") }) {
            Text("Pick a Video")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🎥 Play Video
        videoUri?.let { uri ->
            VideoPlayerView(videoUri = uri)
        }
    }
}
