package com.mido.universitycompanion.data.repository // Ensure correct package name

import com.mido.universitycompanion.data.model.Course
import com.mido.universitycompanion.data.model.Resource


class UniversityRepositoryImpl(private val remoteDataSource: RemoteDataSource) : UniversityRepository {


    override suspend fun getCourses(): List<Course> {
        // 1. بنجيب البيانات الخام من الـ API
        val todoItems = remoteDataSource.getCourses()

        // 2. بنحول كل TodoItem لـ Course (هنا الترجمة بتحصل)
        return todoItems.map { todoItem ->
            Course(
                id = todoItem.id.toString(),
                name = todoItem.title,
                doctorName = "API User ${todoItem.userId}", // بنخترع بيانات من عندنا
                time = if (todoItem.completed) "Completed" else "Pending", // بنخترع بيانات
                location = "Online" // بنخترع بيانات
            )
        }
    }

    override suspend fun getResources(): List<Resource> {
        // 1. بنجيب البيانات الخام من الـ API
        val postItems = remoteDataSource.getResources()

        // 2. بنحول كل PostItem لـ Resource
        return postItems.map { postItem ->
            Resource(
                courseName = "Course ID: ${postItem.userId}", // بنخترع اسم كورس
                resourceName = postItem.title,
                url = "https://example.com/post/${postItem.id}" // بنخترع لينك
            )
        }
    }
}