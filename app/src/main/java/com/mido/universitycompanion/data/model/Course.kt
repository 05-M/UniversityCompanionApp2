package com.mido.universitycompanion.data.model

/**
 * [Course] represents a single course item in the user's schedule.
 * It is a simple data holder for course information.
 *
 * @property id The unique identifier for the course (e.g., "CS101").
 * @property name The full title of the course.
 * @property doctorName The name of the course instructor.
 * @property time The scheduled time for the course (e.g., "Mon 12:00 - 02:00").
 * @property location The physical location or hall number for the lecture.
 */
data class Course(
    val id: String,
    val name: String,
    val doctorName: String,
    val time: String,
    val location: String
)