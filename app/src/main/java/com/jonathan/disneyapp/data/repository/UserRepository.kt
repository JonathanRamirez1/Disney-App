package com.jonathan.disneyapp.data.repository

import com.jonathan.disneyapp.data.model.User
import com.jonathan.disneyapp.api.ApiService
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val apiService: ApiService) {

    suspend fun registerUser(user: User): ApiResponse<User> = withContext(Dispatchers.IO) {
        apiService.registerUser(user)
    }
}