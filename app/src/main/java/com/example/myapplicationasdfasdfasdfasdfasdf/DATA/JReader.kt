package com.example.myapplicationasdfasdfasdfasdfasdf.DATA

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

class JReader {

    object Utilities {
        fun <T> loadJsonFromFile(context: Context, fileName: String, clazz: Class<T>): List<T>? {
            return try {
                // Open the JSON file from the assets folder
                val inputStream = context.assets.open(fileName)

                val reader = BufferedReader(InputStreamReader(inputStream))

                // Define the type for Gson using TypeToken
                val listType = TypeToken.getParameterized(List::class.java, clazz).type





                // Parse the JSON into a list of the specified type
                val items: List<T> = Gson().fromJson(reader, listType)

                reader.close() // Close the reader
                items // Return the list of items
            } catch (e: Exception) {
                e.printStackTrace()
                null // Return null in case of an error
            }
        }
    }
}