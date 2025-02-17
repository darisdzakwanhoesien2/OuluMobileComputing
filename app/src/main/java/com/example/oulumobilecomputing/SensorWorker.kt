package com.example.oulumobilecomputing

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.WorkManager
import androidx.work.ExistingPeriodicWorkPolicy
import kotlin.random.Random

class SensorWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        // Ensure the worker is still active
        val workInfo = WorkManager.getInstance(applicationContext)
            .getWorkInfosForUniqueWork("SensorWorker")
            .get()

        if (workInfo.isEmpty() || workInfo.any { it.state.isFinished }) {
            return Result.success()
        }

        // Simulated sensor reading
        val newReading = "Sensor Value: ${Random.nextInt(20, 100)}"

        // Send a notification
        NotificationHelper.showNotification(applicationContext, "New Sensor Data", newReading)

        return Result.success()
    }
}