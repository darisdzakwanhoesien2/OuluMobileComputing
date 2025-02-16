package com.example.oulumobilecomputing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "ViewA") {
                composable("ViewA") { ViewA(navController) }
                composable("ViewB") { ViewB(navController, this@MainActivity) }
                composable("ViewC") { ViewC(navController, this@MainActivity) }
            }
        }
    }
}