package com.mido.universitycompanion.data.network

import com.mido.universitycompanion.data.model.Course
import retrofit2.http.GET

/**
 * [ApiService] defines the structure of the network API endpoints using Retrofit annotations.
 */
interface ApiService {
    /**
     * Retrieves the list of courses from the defined endpoint.
     * The @GET annotation specifies an HTTP GET request to the "courses.json" path.
     * The [suspend] keyword indicates this is a long-running network operation
     * that must be executed within a coroutine.
     *
     * @return A list of [Course] objects.
     */
    @GET("courses.json")
    suspend fun getCourses(): List<Course>
}