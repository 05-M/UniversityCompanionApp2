package com.mido.universitycompanion.userinterface.viewmodel

import androidx.lifecycle.ViewModel
import com.mido.universitycompanion.data.model.Course
import com.mido.universitycompanion.data.repository.UniversityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * [ScheduleUiState] represents the state of the ScheduleScreen.
 * It holds all data required by the UI to render the course list.
 *
 * @property courses The list of [Course] objects to be displayed.
 */
data class ScheduleUiState(
    val courses: List<Course> = emptyList()
)

/**
 * [ScheduleViewModel] acts as the data source and business logic container for the Schedule screen.
 * It interacts with the [UniversityRepository] to fetch course data and exposes it to the UI via [StateFlow].
 *
 * @param universityRepository The data source, which abstracts where the course data is coming from.
 */
class ScheduleViewModel(private val universityRepository: UniversityRepository): ViewModel() {

    // A mutable StateFlow that can be updated internally by the ViewModel.
    private val _uiState = MutableStateFlow(ScheduleUiState())

    // The read-only StateFlow exposed to the UI, ensuring the UI can only observe and not modify the state.
    val uiState: StateFlow<ScheduleUiState> = _uiState.asStateFlow()

    // The init block is called immediately when the ViewModel is created.
    init {
        getCourses()
    }

    /**
     * Fetches the list of courses from the [UniversityRepository] and updates the UI state.
     */
    private fun getCourses(){
        // Calls the repository to get the course data.
        val courseList = universityRepository.getCourses()
        // Updates the mutable state, which automatically triggers a UI recomposition.
        _uiState.value = ScheduleUiState(courses = courseList)
    }
}