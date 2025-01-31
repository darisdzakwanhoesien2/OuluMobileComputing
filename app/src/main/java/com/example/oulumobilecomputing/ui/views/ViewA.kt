package com.example.oulumobilecomputing.ui.views

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.oulumobilecomputing.services.SensorBackgroundService

import android.os.Build
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewA(navController: NavHostController) {
    val context = LocalContext.current

    Scaffold(
        topBar = { TopAppBar(title = { Text("View A - Main View") }) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it).padding(16.dp)
        ) {
            Text(text = "This is View A", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.navigate("view_b") }) {
                Text("Go to View B")
            }
            Button(onClick = { navController.navigate("view_c") }) {
                Text("Go to View C")
            }
            Button(onClick = { navController.navigate("view_d") }) {
                Text("Go to View D")
            }
            Button(onClick = { navController.navigate("view_e") }) {
                Text("Go to View E")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { startSensorService(context) }) {
                Text("Start Sensor Monitoring")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { stopSensorService(context) }) {
                Text("Stop Sensor Monitoring")
            }
        }
    }
}

fun startSensorService(context: Context) {
    val intent = Intent(context, SensorBackgroundService::class.java)

    // ✅ Fix: Ensure this only runs on Android O (API 26+) or higher
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        context.startForegroundService(intent)
    } else {
        context.startService(intent) // ✅ Use `startService` for Android 24-25
    }
}


fun stopSensorService(context: Context) {
    val intent = Intent(context, SensorBackgroundService::class.java)
    context.stopService(intent)
}




//fun startSensorService(context: Context) {
//    val intent = Intent(context, SensorBackgroundService::class.java)
//    context.startForegroundService(intent)
//}


