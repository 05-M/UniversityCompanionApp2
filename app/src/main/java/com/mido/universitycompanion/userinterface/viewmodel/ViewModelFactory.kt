package com.mido.universitycompanion.userinterface.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.mido.universitycompanion.data.network.RetrofitClient
import com.mido.universitycompanion.data.repository.RemoteDataSourceImpl
import com.mido.universitycompanion.data.repository.UniversityRepositoryImpl

/**
 * [ViewModelFactory] is a custom factory that implements [ViewModelProvider.Factory].
 * It is responsible for creating instances of [ScheduleViewModel] and [ResourcesViewModel],
 * ensuring they are provided with the necessary [UniversityRepositoryImpl] dependency.
 */
object ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

        // 1. إنشاء الـ RemoteDataSource (الطبقة اللي بتكلم API)
        val remoteDataSource = RemoteDataSourceImpl(RetrofitClient.apiService)

        // 2. إنشاء الـ Repository (الطبقة اللي بتستخدم الـ RemoteDataSource)
        val repository = UniversityRepositoryImpl(remoteDataSource)


        // Checks if the requested ViewModel class is assignable from ScheduleViewModel.
        if (modelClass.isAssignableFrom(ScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ScheduleViewModel(repository) as T // Creates and returns the ScheduleViewModel.
        }
        // Checks if the requested ViewModel class is assignable from ResourcesViewModel.
        if (modelClass.isAssignableFrom(ResourcesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ResourcesViewModel(repository) as T // Creates and returns the ResourcesViewModel.
        }
        // Throws an exception if an unknown ViewModel class is requested.
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}