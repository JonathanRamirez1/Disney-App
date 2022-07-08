package com.jonathan.disneyapp.data.repository

import com.jonathan.disneyapp.data.model.User
import com.skydoves.sandwich.ApiResponse

interface UserRepository {

    suspend fun registerUser(user: User): ApiResponse<User>
}