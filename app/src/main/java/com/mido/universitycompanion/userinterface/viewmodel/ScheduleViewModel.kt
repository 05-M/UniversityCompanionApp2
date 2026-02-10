package com.mido.universitycompanion.userinterface.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mido.universitycompanion.data.model.Course
import com.mido.universitycompanion.data.repository.UniversityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * [DataState] represents the overall loading state (Loading, Success and Error)
 */

sealed class DataState{
    data object Loading: DataState()
    data class Success<T>(val data: T): DataState()
    data class Error(val message: String): DataState()
}


/**
 * [ScheduleUiState] represents the state of the ScheduleScreen.
 * It holds all data required by the UI to render the course list.
 *
 * @property courseState The list of [Course] objects to be displayed.
 */
data class ScheduleUiState(
    val courseState: DataState = DataState.Loading
)

/**
 * [ScheduleViewModel] acts as the data source and business logic container for the Schedule screen.
 * It interacts with the [UniversityRepository] to fetch course data and exposes it to the UI via [StateFlow].
 *
 * @param universityRepository The data source, which abstracts where the course data is coming from.
 */
class ScheduleViewModel(private val universityRepository: UniversityRepository): ViewModel() {

    // A mutable StateFlow that can be updated internally by the ViewModel.
    private val _uiState = MutableStateFlow(ScheduleUiState(courseState = DataState.Loading))

    // The read-only StateFlow exposed to the UI, ensuring the UI can only observe and not modify the state.
    val uiState: StateFlow<ScheduleUiState> = _uiState.asStateFlow()

    // The init block is called immediately when the ViewModel is created.
    init {
        getCourses()
    }

    /**
     * Fetches the list of courses from the [UniversityRepository] and updates the UI state.
     */
    fun getCourses() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(courseState = DataState.Loading)
            try {
                val courseList = universityRepository.getCourses()
                _uiState.value = _uiState.value.copy(courseState = DataState.Success(courseList))
            } catch (e: Exception){
                _uiState.value = _uiState.value.copy(
                    courseState = DataState.Error("Failed to load schedule: ${e.localizedMessage ?: "Unknown error"}")
                )
            }
        }
    }
}