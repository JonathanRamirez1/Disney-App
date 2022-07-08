package com.jonathan.disneyapp.utils

import org.json.JSONObject

object MessageResponse {

     fun getErrorMessage(message: String): String {
        val response = JSONObject(message)
        return response.getString("message")
    }
}