package com.example.oulumobilecomputing

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import androidx.compose.ui.viewinterop.AndroidView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoPlayerScreen(navController: NavHostController) {
    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }
    
    var isPlaying by remember { mutableStateOf(true) } // Track play state

    DisposableEffect(Unit) {
        val mediaItem = MediaItem.fromUri("YOUR_VIDEO_URL") // Replace with actual video URL or Uri
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()

        onDispose {
            player.release() // Cleanup when the screen is closed
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Video Player") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)
        ) {
            // Video Player
            AndroidView(
                factory = { PlayerView(context).apply { setPlayer(player) } },
                modifier = Modifier.fillMaxWidth().height(250.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Playback Controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    if (isPlaying) {
                        player.pause()
                    } else {
                        player.play()
                    }
                    isPlaying = !isPlaying
                }) {
                    Text(if (isPlaying) "Pause" else "Play")
                }

                Button(onClick = { player.seekTo(0) }) {
                    Text("Restart")
                }

                Button(onClick = { navController.navigate("main") }) {
                    Text("Back to Main Screen")
                }
            }
        }
    }

    // Handle back press
    BackHandler {
        player.stop()
        navController.popBackStack()
    }
}
