package com.yapss.my_to_do.app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yapss.my_to_do.api.client.ApiClient
import com.yapss.my_to_do.presentation.profile.viewmodel.ProfileViewModel

class ProfileViewModelFactory(
    private val apiClient:ApiClient
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(apiClient = apiClient) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}