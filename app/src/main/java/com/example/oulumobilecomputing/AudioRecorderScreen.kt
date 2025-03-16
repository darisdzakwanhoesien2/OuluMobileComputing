package com.example.oulumobilecomputing

import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioRecorderScreen(navController: NavHostController) {
    val context = LocalContext.current
    val audioFilePath = "${context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)}/recorded_audio.mp3"

    var isRecording by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }

    var mediaRecorder by remember { mutableStateOf<MediaRecorder?>(null) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    fun startRecording() {
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(audioFilePath)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            prepare()
            start()
        }
        isRecording = true
    }

    fun stopRecording() {
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
        isRecording = false
    }

    fun playRecording() {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(audioFilePath)
            prepare()
            start()
            setOnCompletionListener {
                isPlaying = false
            }
        }
        isPlaying = true
    }

    fun stopPlaying() {
        mediaPlayer?.apply {
            stop()
            release()
        }
        mediaPlayer = null
        isPlaying = false
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Audio Recorder") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Record and Play Audio")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { if (isRecording) stopRecording() else startRecording() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isRecording) "Stop Recording" else "Start Recording")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { if (isPlaying) stopPlaying() else playRecording() },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isRecording
            ) {
                Text(if (isPlaying) "Stop Playback" else "Play Recording")
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