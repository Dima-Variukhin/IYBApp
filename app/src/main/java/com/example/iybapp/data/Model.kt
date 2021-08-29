package com.example.iybapp.data

interface Model {
    suspend fun getAction(): ActionUiModel
    suspend fun changeActionStatus(): ActionUiModel?
    fun chooseDataSource(cached: Boolean)
}