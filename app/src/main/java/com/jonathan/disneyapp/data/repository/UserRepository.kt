package com.jonathan.disneyapp.data.repository

import com.jonathan.disneyapp.data.model.User
import com.jonathan.disneyapp.api.ApiService
import com.jonathan.disneyapp.api.IoDispatcher
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService, @IoDispatcher private val ioDispatcher: CoroutineDispatcher) {

    suspend fun registerUser(user: User): ApiResponse<User> = withContext(ioDispatcher) {
        apiService.registerUser(user).let {
            it.onError {  }
            it.onSuccess {

            }
        }
    }
}