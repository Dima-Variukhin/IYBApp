package com.example.iybapp.core.data

import androidx.annotation.StringRes

interface ResourceManager {
    fun getString(@StringRes stringResId: Int): String
}

