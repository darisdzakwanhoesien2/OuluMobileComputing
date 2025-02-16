package com.example.oulumobilecomputing

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import kotlin.random.Random

object SensorUtils {
    fun getSensorReading(context: Context): String {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        return if (sensor != null) {
            "Light Sensor: ${Random.nextInt(10, 500)} lx"
        } else {
            "No Light Sensor Detected"
        }
    }
}
