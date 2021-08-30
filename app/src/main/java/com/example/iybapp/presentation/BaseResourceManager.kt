package com.example.iybapp.presentation

import android.content.Context
import com.example.iybapp.core.data.ResourceManager

class BaseResourceManager(private val context: Context) : ResourceManager {
    override fun getString(stringResId: Int) = context.getString(stringResId)
}