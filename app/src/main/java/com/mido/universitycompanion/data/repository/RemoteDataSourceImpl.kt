package com.mido.universitycompanion.data.repository

import com.mido.universitycompanion.data.model.PostItem
import com.mido.universitycompanion.data.model.TodoItem
import com.mido.universitycompanion.data.network.ApiService

class RemoteDataSourceImpl(private val apiService: ApiService): RemoteDataSource {
    override suspend fun getCourses(): List<TodoItem> {
        return apiService.getCoursesFromApi()
    }

    override suspend fun getResources(): List<PostItem> {
        return apiService.getResourcesFromApi()
    }


}