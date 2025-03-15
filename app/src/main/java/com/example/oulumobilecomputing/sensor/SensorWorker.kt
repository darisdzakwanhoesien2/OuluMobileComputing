package com.example.oulumobilecomputing

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.WorkManager
import androidx.work.ExistingPeriodicWorkPolicy
import kotlin.random.Random

class SensorWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        // ✅ Ensure the worker is still active before proceeding
        val workInfo = WorkManager.getInstance(applicationContext)
            .getWorkInfosForUniqueWork("SensorWorker")
            .get()

        if (workInfo.isEmpty() || workInfo.any { it.state.isFinished }) {
            return Result.success()
        }

        // ✅ Simulate sensor readings
        val sensorReading = Random.nextInt(20, 100)
        val motionValue = Random.nextInt(5, 20)

        // ✅ Send general sensor data notification
        NotificationHelper.showNotification(applicationContext, "New Sensor Data", "Sensor Value: $sensorReading")

        // ✅ Trigger motion-based alerts when the app is in the background
        if (motionValue > 15) {
            NotificationHelper.showNotification(applicationContext, "Background Motion Alert", "Motion detected while app was in the background.")
        }

        return Result.success()
    }
}
