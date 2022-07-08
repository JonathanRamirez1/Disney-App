package com.jonathan.disneyapp.data.api

import com.jonathan.disneyapp.data.model.User
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/auth/register/")
    suspend fun registerUser(@Body users: User): ApiResponse<User>
}