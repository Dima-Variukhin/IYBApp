package com.example.iybapp.data

import com.example.iybapp.ActionDataModel

interface ActionRepository {
    suspend fun getAction(): ActionDataModel
    suspend fun changeActionStatus(): ActionDataModel
    fun chooseDataSource(cached: Boolean)
}