package com.jonathan.disneyapp.data.api

import com.jonathan.disneyapp.data.model.LoginModel
import com.jonathan.disneyapp.data.model.RegisterModel
import com.jonathan.disneyapp.data.payload.LoginResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/auth/register/")
    suspend fun registerUser(@Body registerModel: RegisterModel): ApiResponse<RegisterModel>

    @POST("/auth/login")
    suspend fun loginUser(@Body loginModel: LoginModel): ApiResponse<LoginResponse>
}