package com.mido.universitycompanion.data.repository

import com.mido.universitycompanion.data.model.PostItem
import com.mido.universitycompanion.data.model.TodoItem

interface RemoteDataSource {
    suspend fun getCourses(): List<TodoItem>
    suspend fun getResources(): List<PostItem>
}