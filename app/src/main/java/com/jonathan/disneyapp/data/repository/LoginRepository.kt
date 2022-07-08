package com.jonathan.disneyapp.data.repository

import com.jonathan.disneyapp.data.model.Login
import com.jonathan.disneyapp.data.payload.LoginResponse
import com.skydoves.sandwich.ApiResponse

interface LoginRepository {

    suspend fun loginUser(login: Login): ApiResponse<LoginResponse>
}