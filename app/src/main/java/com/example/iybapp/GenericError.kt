package com.example.iybapp

import com.example.iybapp.data.ResourceManager

class GenericError(private val resourceManager: ResourceManager) : ActionFailure {
    override fun getMessage() = resourceManager.getString(R.string.generic_fail_message)
}