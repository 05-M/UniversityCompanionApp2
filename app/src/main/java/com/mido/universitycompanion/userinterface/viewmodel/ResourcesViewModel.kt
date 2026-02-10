package com.mido.universitycompanion.userinterface.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mido.universitycompanion.data.model.Resource
import com.mido.universitycompanion.data.repository.UniversityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * [ResourcesUiState] represents the state of the ResourcesScreen.
 * It holds all data required by the UI to render the list of resources.
 *
 * @property resources The list of [Resource] objects to be displayed.
 */
data class ResourcesUiState(
    val resourceState: DataState = DataState.Loading
)

/**
 * [ResourcesViewModel] acts as the data source and business logic container for the Resources screen.
 * It interacts with the [UniversityRepository] to fetch resource data and exposes it to the UI via [StateFlow].
 *
 * @param universityRepository The data source, which abstracts where the resource data is coming from.
 */
class ResourcesViewModel(private val universityRepository: UniversityRepository): ViewModel(){

    // A mutable StateFlow that can be updated internally by the ViewModel.
    private val _uiState = MutableStateFlow(ResourcesUiState())

    // The read-only StateFlow exposed to the UI, ensuring the UI can only observe and not modify the state.
    val uiState: StateFlow<ResourcesUiState> = _uiState.asStateFlow()

    // The init block is called immediately when the ViewModel is created.
    init {
        getResources()
    }

    /**
     * Fetches the list of resources from the [UniversityRepository] and updates the UI state.
     */
    fun getResources() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(resourceState = DataState.Loading)
            try {
                val resourceList = universityRepository.getResources()
                _uiState.value = _uiState.value.copy(resourceState = DataState.Success(resourceList))
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    resourceState = DataState.Error("Failed to load resources: ${e.localizedMessage ?: "Unknown error"}")
                )
            }
        }
    }
}
