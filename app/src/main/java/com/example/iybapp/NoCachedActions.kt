package com.example.iybapp

import com.example.iybapp.data.ResourceManager

class NoCachedActions(private val resourceManager: ResourceManager) : ActionFailure {
    override fun getMessage() = resourceManager.getString(R.string.no_cached_actions)
}