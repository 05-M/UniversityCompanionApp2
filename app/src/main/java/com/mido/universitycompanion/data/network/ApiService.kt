package com.mido.universitycompanion.data.network

import com.mido.universitycompanion.data.model.PostItem
import com.mido.universitycompanion.data.model.TodoItem
import retrofit2.http.GET

/**
 * [ApiService] defines the structure of the network API endpoints using Retrofit annotations.
 */
interface ApiService {

    @GET("todos")
    suspend fun getCoursesFromApi(): List<TodoItem>

    @GET("posts")
    suspend fun getResourcesFromApi(): List<PostItem>
}