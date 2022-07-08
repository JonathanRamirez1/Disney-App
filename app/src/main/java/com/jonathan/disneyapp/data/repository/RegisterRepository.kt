package com.jonathan.disneyapp.data.repository

import com.jonathan.disneyapp.data.model.Register
import com.skydoves.sandwich.ApiResponse

interface RegisterRepository {

    suspend fun registerUser(register: Register): ApiResponse<Register>
}