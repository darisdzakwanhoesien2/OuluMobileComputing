package com.example.mobilecomputingproject.media

import android.Manifest
import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.speech.RecognizerIntent
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.io.File

@Composable
fun AudioRecorder() {
    val context = LocalContext.current
    val audioFile = File(context.getExternalFilesDir(null), "audio_record.3gp")

    var mediaRecorder by remember { mutableStateOf<MediaRecorder?>(null) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isRecording by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }
    var recognizedText by remember { mutableStateOf("Press 'Start Speech' to recognize speech") }

    // Handle runtime permissions
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (!isGranted) {
                Toast.makeText(context, "Microphone permission is required", Toast.LENGTH_LONG).show()
            }
        }
    )

    LaunchedEffect(Unit) {
        requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Record Button
        Button(onClick = {
            if (!isRecording) {
                mediaRecorder = MediaRecorder().apply {
                    setAudioSource(MediaRecorder.AudioSource.MIC)
                    setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                    setOutputFile(audioFile.absolutePath)
                    setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                    prepare()
                    start()
                }
                isRecording = true
            } else {
                mediaRecorder?.apply {
                    stop()
                    release()
                }
                mediaRecorder = null
                isRecording = false
            }
        }) {
            Text(if (isRecording) "Stop Recording" else "Start Recording")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Play Button
        Button(onClick = {
            if (!isPlaying) {
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(audioFile.absolutePath)
                    prepare()
                    start()
                    setOnCompletionListener {
                        isPlaying = false
                    }
                }
                isPlaying = true
            } else {
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = null
                isPlaying = false
            }
        }) {
            Text(if (isPlaying) "Stop Playback" else "Play Recording")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Speech Recognition Button
        Button(onClick = { startSpeechRecognition(context) { recognizedText = it } }) {
            Text("Start Speech Recognition")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Recognized Speech Text
        Text(text = recognizedText)
    }
}