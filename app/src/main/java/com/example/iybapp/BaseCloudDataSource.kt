package com.example.iybapp

import java.lang.Exception
import java.net.UnknownHostException

class BaseCloudDataSource(private val service: ActionService) : CloudDataSource {
    override suspend fun getAction(): Result<ActionServerModel, ErrorType> {
        return try {
            val result = service.getAction()
            Result.Success(result)
        } catch (e: Exception) {
            val errorType = if (e is UnknownHostException)
                ErrorType.NO_CONNECTION
            else
                ErrorType.SERVER_UNAVAILABLE
            Result.Error(errorType)
        }
    }
}