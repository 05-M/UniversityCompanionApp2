package com.mido.universitycompanion.userinterface.viewmodel

import androidx.lifecycle.ViewModel
import com.mido.universitycompanion.data.model.Resource
import com.mido.universitycompanion.data.repository.UniversityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * [ResourcesUiState] represents the state of the ResourcesScreen.
 * It holds all data required by the UI to render the list of resources.
 *
 * @property resources The list of [Resource] objects to be displayed.
 */
data class ResourcesUiState(
    val resources: List<Resource> = emptyList()
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
    private fun getResources() {
        // Calls the repository to get the resource data.
        val resourceList = universityRepository.getResources()
        // Updates the mutable state, which automatically triggers a UI recomposition.
        _uiState.value = ResourcesUiState(resources = resourceList)
    }
}