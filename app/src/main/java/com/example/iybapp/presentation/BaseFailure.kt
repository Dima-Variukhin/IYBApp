package com.example.iybapp.presentation

import androidx.annotation.StringRes
import com.example.iybapp.R
import com.example.iybapp.core.data.ResourceManager
import com.example.iybapp.core.presentation.Failure

abstract class BaseFailure(private val resourceManager: ResourceManager) : Failure {

    @StringRes
    protected abstract fun getMessageResId(): Int

    override fun getMessage(): String = resourceManager.getString(getMessageResId())
}

class NoConnection(private val resourceManager: ResourceManager) : Failure {
    override fun getMessage() = resourceManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val resourceManager: ResourceManager) : Failure {
    override fun getMessage() = resourceManager.getString(R.string.service_unavailable)
}