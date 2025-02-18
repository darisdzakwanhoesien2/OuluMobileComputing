package com.example.oulumobilecomputing

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.work.*
import java.util.concurrent.TimeUnit
import kotlin.math.sqrt

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity(), SensorEventListener {

    private val sensorData = mutableStateOf("No Data Yet")
    private var isMonitoring = mutableStateOf(false)

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var lastShakeTime: Long = 0

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Ensure notification channel is created
        NotificationHelper.createNotificationChannel(this)

        // ✅ Initialize SensorManager for motion detection
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        setContent {
            val permissionGranted = remember { mutableStateOf(false) }

            // ✅ System pop-up for permission request
            val requestPermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                permissionGranted.value = isGranted
                if (isGranted) {
                    Log.d("Permission", "Notifications allowed")
                } else {
                    Log.w("Permission", "Notifications denied")
                }
            }

            // ✅ Default: Permission NOT granted
            permissionGranted.value = false

            // ✅ Automatically ask for permission if not granted
            LaunchedEffect(Unit) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (ContextCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        permissionGranted.value = true
                    } else {
                        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                }
            }

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

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = if (permissionGranted.value) "✅ Notifications Enabled"
                        else "❌ Notifications Denied",
                        color = if (permissionGranted.value) Color.Green else Color.Red
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS) }) {
                        Text("Request Notification Permission")
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Motion Sensor: Shake your phone to trigger an ESG alert!")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (it.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                detectShake(it.values[0], it.values[1], it.values[2])
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not needed for this implementation
    }

    private fun detectShake(x: Float, y: Float, z: Float) {
        val acceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
        val shakeThreshold = 15f
        val currentTime = System.currentTimeMillis()

        if (acceleration > shakeThreshold && (currentTime - lastShakeTime) > 1000) {
            lastShakeTime = currentTime
            triggerMotionAlert()
        }
    }

    private fun triggerMotionAlert() {
        NotificationHelper.showNotification(this, "Motion Detected", "You shook your phone! ESG alert triggered.")
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
            .setConstraints(
                Constraints.Builder()
                    .setRequiresBatteryNotLow(true)
                    .setRequiresDeviceIdle(false)
                    .setRequiresCharging(false)
                    .build()
            )
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
