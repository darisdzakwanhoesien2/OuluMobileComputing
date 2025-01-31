package com.example.oulumobilecomputing.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.example.oulumobilecomputing.notifications.NotificationHelper

class SensorHandler(private val context: Context, private val sensorManager: SensorManager) : SensorEventListener {
    private var lightSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

    fun startListening() {
        lightSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    fun stopListening() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val lightLevel = it.values[0]
            if (lightLevel > 1000) { // Threshold example
                NotificationHelper.showNotification(context, "High light detected: $lightLevel")
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
