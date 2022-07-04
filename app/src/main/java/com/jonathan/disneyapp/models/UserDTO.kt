package com.jonathan.disneyapp.models

import com.google.gson.annotations.SerializedName

data class UserDTO(
   // @SerializedName("id") val id: Long,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("role") val roles: ArrayList<String>
)
