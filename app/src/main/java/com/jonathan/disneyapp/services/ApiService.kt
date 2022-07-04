package com.jonathan.disneyapp.services

import com.jonathan.disneyapp.models.UserDTO
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/auth/register/")
    suspend fun registerUser(@Body users: UserDTO): ApiResponse<UserDTO>
}