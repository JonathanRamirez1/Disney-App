package com.jonathan.disneyapp.data.repository

import com.jonathan.disneyapp.data.api.ApiService
import com.jonathan.disneyapp.di.IoDispatcher
import com.jonathan.disneyapp.data.model.Register
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RegisterRepository {

    override suspend fun registerUser(register: Register): ApiResponse<Register> = withContext(ioDispatcher) {
        apiService.registerUser(register)
    }
}