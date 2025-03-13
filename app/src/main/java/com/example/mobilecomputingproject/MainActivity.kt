package com.example.mobilecomputingproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilecomputingproject.database.MessageViewModel
import com.example.mobilecomputingproject.media.AudioRecorder
import com.example.mobilecomputingproject.media.CameraCapture
import com.example.mobilecomputingproject.ui.MessageScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MessageViewModel = viewModel()
            Column {
                MessageScreen(viewModel) 
                AudioRecorder()
                CameraCapture()
                VideoPlayer()
            }
        }
    }
}
