package com.example.iybapp

interface Model {
    suspend fun getAction(): ActionUiModel
    suspend fun changeActionStatus(): ActionUiModel?
    fun chooseDataSource(cached: Boolean)
}