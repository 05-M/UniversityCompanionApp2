package com.mido.universitycompanion.data.model

/**
 * [Resource] represents a single educational resource link.
 * It is a simple data holder for resource information.
 *
 * @property courseName The name of the course this resource belongs to.
 * @property resourceName The title/name of the resource (e.g., "Lecture 1 Slides").
 * @property url The direct URL link to the resource.
 */
data class Resource(
    val courseName: String,
    val resourceName: String,
    val url: String
)