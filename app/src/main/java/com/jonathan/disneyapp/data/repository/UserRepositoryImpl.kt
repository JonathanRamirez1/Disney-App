package com.jonathan.disneyapp.data.repository

import com.jonathan.disneyapp.data.api.ApiService
import com.jonathan.disneyapp.di.IoDispatcher
import com.jonathan.disneyapp.data.model.User
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun registerUser(user: User): ApiResponse<User> = withContext(ioDispatcher) {
        apiService.registerUser(user)
    }
}