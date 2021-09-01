package com.example.iybapp

import com.example.iybapp.core.data.ResourceManager
import com.example.iybapp.presentation.BaseFailure

class NoCachedData(resourceManager: ResourceManager) : BaseFailure(resourceManager) {
    override fun getMessageResId() = R.string.no_cached_data
}