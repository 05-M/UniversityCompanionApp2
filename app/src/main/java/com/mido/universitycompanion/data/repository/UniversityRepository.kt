package com.mido.universitycompanion.data.repository // Ensure correct package name

import com.mido.universitycompanion.data.model.Course
import com.mido.universitycompanion.data.model.Resource

/**
 * [UniversityRepository] is the contract (interface) for the data layer.
 * It abstracts the source of the data, so the ViewModel does not need to know
 * whether the data comes from a local database, a network call, or a fake source.
 */
interface UniversityRepository {
    /**
     * Retrieves the list of all courses.
     * @return A list of [Course] objects.
     */
    fun getCourses(): List<Course>

    /**
     * Retrieves the list of educational resources.
     * @return A list of [Resource] objects.
     */
    fun getResources(): List<Resource>
}