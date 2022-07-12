package com.jonathan.disneyapp.data.repository

import com.jonathan.disneyapp.data.database.entity.RegisterEntity
import com.jonathan.disneyapp.data.model.RegisterModel
import com.jonathan.disneyapp.domain.model.Register
import com.skydoves.sandwich.ApiResponse

interface RegisterRepository {

    suspend fun registerUserFromApi(register: Register): ApiResponse<List<Register>>
    suspend fun insertUser(registerEntity: List<RegisterEntity>)
}