package com.example.iybapp

import com.example.iybapp.data.ResourceManager
import com.example.iybapp.domain.Action
import com.example.iybapp.domain.NoCachedActionsException
import com.example.iybapp.domain.NoConnectionException
import com.example.iybapp.domain.ServiceUnavailableException
import java.lang.Exception

interface ActionFailureHandler {
    fun handle(e: Exception): ActionFailure
}

class ActionFailureFactory(private val resourceManager: ResourceManager) : ActionFailureHandler {
    override fun handle(e: Exception): ActionFailure {
        return when (e) {
            is NoConnectionException -> NoConnection(resourceManager)
            is NoCachedActionsException -> NoCachedActions(resourceManager)
            is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
            else -> GenericError(resourceManager)
        }
    }
}