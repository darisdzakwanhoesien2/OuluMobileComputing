package com.example.oulumobilecomputing.ui.views

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.oulumobilecomputing.services.SensorBackgroundService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewA(navController: NavHostController) {
    val context = LocalContext.current

    // ✅ State to hold the light sensor value
    var lightLevel by remember { mutableFloatStateOf(0f) }

    // ✅ Register BroadcastReceiver to receive sensor data
    DisposableEffect(context) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == "com.example.oulumobilecomputing.SENSOR_DATA") {
                    lightLevel = intent.getFloatExtra("light_level", 0f)
                }
            }
        }

        val filter = IntentFilter("com.example.oulumobilecomputing.SENSOR_DATA")

        // ✅ Add receiver flags for Android 12+ to prevent the warning
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13+
            context.registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            context.registerReceiver(receiver, filter)
        }

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("View A - Main View") }) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it).padding(16.dp)
        ) {
            Text(text = "This is View A", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            // ✅ Display the light sensor data
            Text(
                text = "Current Light Level: $lightLevel lux",
                style = MaterialTheme.typography.bodyLarge
            )

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
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        context.startForegroundService(intent)
    } else {
        context.startService(intent)
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


