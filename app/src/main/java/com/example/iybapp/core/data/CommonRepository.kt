package com.example.iybapp.core.data

interface CommonRepository {
    suspend fun getCommonItem(): CommonDataModel
    suspend fun changeStatus(): CommonDataModel
    fun chooseDataSource(cached: Boolean)
}