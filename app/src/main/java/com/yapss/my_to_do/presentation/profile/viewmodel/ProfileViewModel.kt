package com.yapss.my_to_do.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import com.yapss.my_to_do.api.client.ApiClient

class ProfileViewModel(
    private val apiClient:ApiClient
):ViewModel() {
    suspend fun getConnectionMessage():String{
        return try {
            val response = apiClient.apiService.getConnectionMessage()
            if (response.isSuccessful) {
                response.body()?.message ?: "Connected but message is null"
            } else {
                "Can not connected" //"Error: ${response.code()} - ${response.errorBody()?.string()}"
            }
        } catch (e: Exception) {
            "Exception" //"Exception: ${e.message}"
        }
    }
}