package com.example.oulumobilecomputing

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

object DataStoreUtils {
    private val USERNAME_KEY = stringPreferencesKey("username")
    private val IMAGE_URI_KEY = stringPreferencesKey("image_uri")

    suspend fun saveUserData(context: Context, username: String, imageUri: String) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = username
            preferences[IMAGE_URI_KEY] = imageUri
        }
    }

    fun getUsername(context: Context): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[USERNAME_KEY]
        }
    }

    fun getImageUri(context: Context): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[IMAGE_URI_KEY]
        }
    }
}
