package com.example.oulumobilecomputing

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oulumobilecomputing.ui.theme.OuluMobileComputingTheme
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OuluMobileComputingTheme {
                MyNavigationApp()
            }
        }
    }
}

@Composable
fun MyNavigationApp() {
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "ViewA",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("ViewA") { ViewA(navController) }
            composable("ViewB") { ViewB(navController) }
        }
    }
}


