package com.example.mobilecomputingproject.media

import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Environment
import androidx.compose.material.*
import androidx.compose.runtime.*
import java.io.File

@Composable
fun AudioRecorder() {
    var mediaRecorder: MediaRecorder? = null
    val mediaPlayer = remember { MediaPlayer() }
    val audioFile = File(Environment.getExternalStorageDirectory(), "audio.3gp")
    var isRecording by remember { mutableStateOf(false) }

    Column {
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
                mediaRecorder?.stop()
                mediaRecorder?.release()
                isRecording = false
            }
        }) {
            Text(if (isRecording) "Stop Recording" else "Start Recording")
        }

        Button(onClick = {
            mediaPlayer.apply {
                setDataSource(audioFile.absolutePath)
                prepare()
                start()
            }
        }) {
            Text("Play Recording")
        }
    }
}
