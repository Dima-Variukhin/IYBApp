package com.example.iybapp

interface ActionFailure {
    fun getMessage(): String
}

class NoConnection(private val resourceManager: ResourceManager) : ActionFailure {
    override fun getMessage() = resourceManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val resourceManager: ResourceManager) : ActionFailure {
    override fun getMessage() = resourceManager.getString(R.string.service_unavailable)
}