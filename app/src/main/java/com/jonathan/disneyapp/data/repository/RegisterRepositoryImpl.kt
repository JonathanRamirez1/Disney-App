package com.jonathan.disneyapp.data.repository

import com.jonathan.disneyapp.data.api.ApiService
import com.jonathan.disneyapp.data.database.dao.RegisterDao
import com.jonathan.disneyapp.data.database.entity.RegisterEntity
import com.jonathan.disneyapp.data.model.RegisterModel
import com.jonathan.disneyapp.domain.model.Register
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val registerDao: RegisterDao
) : RegisterRepository {

    override suspend fun registerUserFromApi(register: Register): ApiResponse<List<Register>> {
      val response: RegisterModel = apiService.registerUser(register)
    }

    override suspend fun insertUser(registerEntity: List<RegisterEntity>) {
        registerDao.insertUser(registerEntity)
    }
}