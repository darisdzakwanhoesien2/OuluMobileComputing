package com.example.oulumobilecomputing

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.work.*

import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    private val sensorData = mutableStateOf("No Data Yet")
    private var isMonitoring = mutableStateOf(false)

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        requestNotificationPermission()

        setContent {
            Scaffold(
                topBar = { TopAppBar(title = { Text("Oulu Mobile Computing") }) }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp)
                ) {
                    Text("Task 4: Sensors & Notifications Feature")
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = { toggleMonitoring() }) {
                        Text(if (isMonitoring.value) "Stop Background Monitoring" else "Start Background Monitoring")
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Sensor Data: ${sensorData.value}")
                }
            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
            }
        }
    }

    private fun toggleMonitoring() {
        if (isMonitoring.value) {
            stopSensorWorker()
        } else {
            startSensorWorker()
        }
        isMonitoring.value = !isMonitoring.value
    }

    private fun startSensorWorker() {
        val workRequest = PeriodicWorkRequestBuilder<SensorWorker>(15, TimeUnit.MINUTES)
            .setConstraints(Constraints.Builder().setRequiresBatteryNotLow(true).build())
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "SensorWorker",
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }

    private fun stopSensorWorker() {
        WorkManager.getInstance(this).cancelUniqueWork("SensorWorker")
        sensorData.value = "Monitoring Stopped"
    }

    fun updateSensorData(newData: String) {
        sensorData.value = newData
    }
}