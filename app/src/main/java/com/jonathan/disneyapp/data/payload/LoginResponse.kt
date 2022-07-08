package com.jonathan.disneyapp.data.payload

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("type") val type: String = "Bearer",
    @SerializedName("token") val token: String,
    @SerializedName("role") val roles: ArrayList<String>
)
