package com.example.oulumobilecomputing

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.oulumobilecomputing.ViewA
import com.example.oulumobilecomputing.ViewB
import com.example.oulumobilecomputing.ViewC
import com.example.oulumobilecomputing.CameraScreen
import com.example.oulumobilecomputing.AudioRecorderScreen
import com.example.oulumobilecomputing.VideoPlayerScreen

@Composable
fun AppNavigation(navController: NavHostController) { // ✅ Fix: Accept navController as parameter
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("viewA") { ViewA(navController) }
        composable("viewB") { ViewB(navController) }
        composable("viewC") { ViewC(navController) }
        composable("camera") { CameraScreen(navController) } // ✅ Camera Feature
        composable("audio") { AudioRecorderScreen(navController) } // ✅ Audio Recording
        composable("video") { VideoPlayerScreen(navController) } // ✅ Video Playback
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Task 4: Sensors & Notifications Feature", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("viewA") }) {
            Text("Go to View A")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("viewB") }) {
            Text("Go to View B")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("viewC") }) {
            Text("Go to View C")
        }
    }
}
