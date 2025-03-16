package com.example.oulumobilecomputing

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.work.*
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.math.sqrt

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var lastShakeTime: Long = 0
    private val sensorData = mutableStateOf("No Data Yet")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Ensure notification channel is created
        NotificationHelper.createNotificationChannel(this)

        // ✅ Initialize SensorManager for motion detection
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        // ✅ Schedule background sensor worker
        scheduleSensorWorker()

        setContent {
            val navController = rememberNavController()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()

            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    DrawerContent(navController) {
                        scope.launch { drawerState.close() } // ✅ Fix: Ensure it runs inside a coroutine
                    }
                }
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Oulu Mobile Computing") },
                            navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch { drawerState.open() } // ✅ Fix: Call inside coroutine
                                }) {
                                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                    ) {
                        AppNavigation(navController) // ✅ Fix: Pass `navController`
                    }
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

    private fun scheduleSensorWorker() {
        val workRequest = PeriodicWorkRequestBuilder<SensorWorker>(15, TimeUnit.MINUTES)
            .setConstraints(
                Constraints.Builder()
                    .setRequiresBatteryNotLow(true)  // ✅ Only run if battery is not low
                    .setRequiresDeviceIdle(false)  // ✅ Can run while device is in use
                    .setRequiresCharging(false)  // ✅ Runs on battery power
                    .build()
            )
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "SensorWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}

@Composable
fun DrawerContent(navController: androidx.navigation.NavController, closeDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Navigation", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        DrawerButton("Camera", "camera", navController, closeDrawer)
        DrawerButton("Audio Recorder", "audio", navController, closeDrawer)
        DrawerButton("Video Player", "video", navController, closeDrawer)
    }
}

@Composable
fun DrawerButton(title: String, route: String, navController: androidx.navigation.NavController, closeDrawer: () -> Unit) {
    Button(
        onClick = {
            navController.navigate(route) {
                popUpTo("main") { inclusive = false }
            }
            closeDrawer()
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(title)
    }
}