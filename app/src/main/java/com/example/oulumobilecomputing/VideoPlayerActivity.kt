// VideoPlayerActivity.kt
package com.example.oulumobilecomputing

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.ComponentActivity

class VideoPlayerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val videoView = findViewById<VideoView>(R.id.videoView)
        val videoPath = "android.resource://${packageName}/${R.raw.sample_video}"
        val uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)
        videoView.setMediaController(MediaController(this))
        videoView.start()
    }
}