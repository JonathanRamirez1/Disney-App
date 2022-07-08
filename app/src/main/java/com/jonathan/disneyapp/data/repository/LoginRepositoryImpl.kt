package com.jonathan.disneyapp.data.repository

import com.jonathan.disneyapp.data.api.ApiService
import com.jonathan.disneyapp.data.model.Login
import com.jonathan.disneyapp.data.payload.LoginResponse
import com.jonathan.disneyapp.di.IoDispatcher
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): LoginRepository {

    override suspend fun loginUser(login: Login): ApiResponse<LoginResponse> = withContext(ioDispatcher) {
        apiService.loginUser(login)
    }
}