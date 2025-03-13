package com.example.mobilecomputingproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobilecomputingproject.database.MessageViewModel
import com.example.mobilecomputingproject.media.AudioRecorder
import com.example.mobilecomputingproject.media.CameraCapture
import com.example.mobilecomputingproject.media.VideoPlayer
import com.example.mobilecomputingproject.ui.MessageScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MessageViewModel = viewModel()

            Scaffold(
                topBar = {
                    TopAppBar(title = { Text("Mobile Computing Project") })
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(16.dp)
                ) {
                    MessageScreen(viewModel)  // Handles messages using Room Database
                    Spacer(modifier = Modifier.height(16.dp))

                    AudioRecorder()  // Microphone functionality (Record & Speech-to-Text)
                    Spacer(modifier = Modifier.height(16.dp))

                    CameraCapture()  // Camera (Capture & Display Photos)
                    Spacer(modifier = Modifier.height(16.dp))

                    VideoPlayer()  // Video Playback (Pick & Play Videos)
                }
            }
        }
    }
}