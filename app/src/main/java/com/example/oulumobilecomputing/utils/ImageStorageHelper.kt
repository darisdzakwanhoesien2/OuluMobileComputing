package com.example.oulumobilecomputing

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object ImageStorageHelper {
    private const val IMAGE_FILE_NAME = "profile_image.jpg"

    fun saveImage(context: Context, imageUri: Uri): String {
        val file = File(context.filesDir, IMAGE_FILE_NAME)
        val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri)
        val outputStream = FileOutputStream(file)

        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()

        return file.absolutePath
    }

    fun getSavedImagePath(context: Context): String? {
        val file = File(context.filesDir, IMAGE_FILE_NAME)
        return if (file.exists()) file.absolutePath else null
    }
}