package com.example.iybapp

import retrofit2.http.GET

interface ActionService {
    @GET("https://www.boredapi.com/api/activity")
    suspend fun getAction(): ActionServerModel
}

enum class ErrorType {
    NO_CONNECTION,
    SERVER_UNAVAILABLE
}