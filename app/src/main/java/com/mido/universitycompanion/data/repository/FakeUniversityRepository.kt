package com.mido.universitycompanion.data.repository // Ensure correct package name

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mido.universitycompanion.data.model.Course
import com.mido.universitycompanion.data.model.Resource
import java.io.InputStreamReader
import java.lang.reflect.Type // Import the correct Type

/**
 * [FakeUniversityRepository] is the implementation of [UniversityRepository] for this project iteration.
 * It simulates a network data source by reading JSON files directly from the app's 'assets' folder.
 *
 * @param context The application [Context], required to access the 'assets' folder.
 */
class FakeUniversityRepository(private val context: Context) : UniversityRepository {

    /**
     * Generic private function to read and parse a JSON file from the 'assets' folder.
     * This function encapsulates the logic for accessing the Android AssetManager and using Gson.
     *
     * @param fileName The name of the JSON file in the 'assets' folder (e.g., "courses.json").
     * @param type The generic [Type] (obtained via [TypeToken]) of the list being deserialized (e.g., List<Course>).
     * @return A list of objects of the specified type [T].
     */
    private fun <T> readJsonFromAsset(fileName: String, type: Type): List<T> {
        return context.assets.open(fileName).use { inputStream -> // Opens the asset file.
            InputStreamReader(inputStream).use { reader -> // Reads the content of the file.
                Gson().fromJson(reader, type) // Uses Gson to convert the JSON string to the target List<T>.
            }
        }
    }

    /**
     * Retrieves the list of courses by reading and parsing the local 'courses.json' file.
     *
     * @return A list of [Course] objects.
     */
    override fun getCourses(): List<Course> {
        val type = object : TypeToken<List<Course>>() {}.type
        return readJsonFromAsset("courses.json", type)
    }

    /**
     * Retrieves the list of resources by reading and parsing the local 'resources.json' file.
     *
     * @return A list of [Resource] objects.
     */
    override fun getResources(): List<Resource> {
        val type = object : TypeToken<List<Resource>>() {}.type
        return readJsonFromAsset("resources.json", type)
    }
}