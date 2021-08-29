package com.example.iybapp

import com.example.iybapp.data.ResourceManager

interface ActionFailure {
    fun getMessage(): String
}

class NoConnection(private val resourceManager: ResourceManager) : ActionFailure {
    override fun getMessage() = resourceManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val resourceManager: ResourceManager) : ActionFailure {
    override fun getMessage() = resourceManager.getString(R.string.service_unavailable)
}