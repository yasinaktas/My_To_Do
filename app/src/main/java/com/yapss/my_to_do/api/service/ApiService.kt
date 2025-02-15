package com.yapss.my_to_do.api.service

import com.yapss.my_to_do.data.model.ConnectionResult
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("rest/api/test/connectionMessage")
    suspend fun getConnectionMessage(): Response<ConnectionResult>

}