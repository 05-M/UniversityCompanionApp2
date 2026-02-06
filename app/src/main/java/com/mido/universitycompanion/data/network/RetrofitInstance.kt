package com.mido.universitycompanion.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * [RetrofitInstance] is a Singleton object responsible for creating and providing
 * the [ApiService] instance for network calls.
 * This ensures that only one Retrofit client is created throughout the application's lifecycle.
 */
object RetrofitInstance {
    // This is the base URL for the API. It is a fake URL as we are using local JSON assets
    // to simulate a network call in the current iteration of the project.
    private const val BASE_URL = "https://fake.api/"

    /**
     * The lazily initialized [ApiService] instance.
     * [lazy] ensures that the Retrofit object is created only when it is accessed for the first time.
     */
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Sets the base URL for the API.
            // Uses GsonConverterFactory to handle the serialization and deserialization of JSON data.
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            // Creates the implementation of the ApiService interface.
            .create(ApiService::class.java)
    }
}