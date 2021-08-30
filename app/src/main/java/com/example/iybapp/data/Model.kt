package com.example.iybapp.data

import com.example.iybapp.presentation.CommonUiModel

interface Model {
    suspend fun getAction(): CommonUiModel
    suspend fun changeActionStatus(): CommonUiModel?
    fun chooseDataSource(cached: Boolean)
}