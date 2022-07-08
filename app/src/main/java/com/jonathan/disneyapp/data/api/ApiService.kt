package com.jonathan.disneyapp.data.api

import com.jonathan.disneyapp.data.model.Login
import com.jonathan.disneyapp.data.model.Register
import com.jonathan.disneyapp.data.payload.LoginResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/auth/register/")
    suspend fun registerUser(@Body register: Register): ApiResponse<Register>

    @POST("/auth/login")
    suspend fun loginUser(@Body login: Login): ApiResponse<LoginResponse>
}