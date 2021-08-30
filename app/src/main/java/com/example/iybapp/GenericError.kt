package com.example.iybapp

import com.example.iybapp.core.data.ResourceManager
import com.example.iybapp.core.presentation.Failure

class GenericError(private val resourceManager: ResourceManager) : Failure {
    override fun getMessage() = resourceManager.getString(R.string.generic_fail_message)
}