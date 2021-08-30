package com.example.iybapp.domain

import com.example.iybapp.GenericError
import com.example.iybapp.NoCachedData
import com.example.iybapp.core.data.ResourceManager
import com.example.iybapp.core.domain.FailureHandler
import com.example.iybapp.core.domain.NoCachedDataException
import com.example.iybapp.core.domain.NoConnectionException
import com.example.iybapp.core.domain.ServiceUnavailableException
import com.example.iybapp.core.presentation.Failure
import com.example.iybapp.presentation.NoConnection
import com.example.iybapp.presentation.ServiceUnavailable

class FailureFactory(private val resourceManager: ResourceManager) : FailureHandler {
    override fun handle(e: Exception): Failure {
        return when (e) {
            is NoConnectionException -> NoConnection(resourceManager)
            is NoCachedDataException -> NoCachedData(resourceManager)
            is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
            else -> GenericError(resourceManager)
        }
    }
}