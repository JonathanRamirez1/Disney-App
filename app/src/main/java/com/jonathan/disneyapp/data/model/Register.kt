package com.jonathan.disneyapp.data.model

import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("role") val roles: ArrayList<String>
)
