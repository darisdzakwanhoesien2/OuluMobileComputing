package com.example.oulumobilecomputing.repository

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import com.example.oulumobilecomputing.database.AppDatabase
import com.example.oulumobilecomputing.database.UserEntity
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class UserRepository(private val database: AppDatabase, private val context: Context) {

    suspend fun saveUserData(name: String, imageUri: Uri?): String? {
        var savedImagePath: String? = null

        imageUri?.let {
            val inputStream: InputStream? = context.contentResolver.openInputStream(it)
            val file = File(context.filesDir, "profile_image.jpg")
            val outputStream = FileOutputStream(file)

            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()

            savedImagePath = file.absolutePath
        }

        val userEntity = UserEntity(name = name, imagePath = savedImagePath ?: "")
        database.userDao().insertUserData(userEntity)

        return savedImagePath
    }

    suspend fun getUserData(): UserEntity? {
        return database.userDao().getUserData()
    }
}
