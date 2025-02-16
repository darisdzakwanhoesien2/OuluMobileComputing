package com.example.oulumobilecomputing

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

object ApiFetcher {

    suspend fun getWeatherData(context: Context): String? {
        return withContext(Dispatchers.IO) {
            try {
                val apiKey = "YOUR_OPENWEATHER_API_KEY"
                val url = "https://api.openweathermap.org/data/2.5/weather?q=Oulu&appid=$apiKey"
                val response = URL(url).readText()
                val jsonObject = JSONObject(response)
                jsonObject.getJSONArray("weather").getJSONObject(0).getString("description")
            } catch (e: Exception) {
                null
            }
        }
    }
}
