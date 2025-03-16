package com.example.oulumobilecomputing

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("main/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Guest"
            MainScreen(navController, username) // ✅ Uses correct import from MainScreen.kt
        }
        composable("viewA") { ViewA(navController) }
        composable("viewB") { ViewB(navController) }
        composable("viewC") { ViewC(navController) }
        composable("camera") { CameraScreen(navController) }
        composable("audio") { AudioRecorderScreen(navController) }
        composable("video") { VideoPlayerScreen(navController) }
    }
}
