package com.jonathan.disneyapp.domain.model

import com.jonathan.disneyapp.data.model.RegisterModel

data class Register(
    val username: String,
    val email: String,
    val password: String,
    val roles: ArrayList<String>
)

fun RegisterModel.toDomain() = Register(username, email, password, roles)
