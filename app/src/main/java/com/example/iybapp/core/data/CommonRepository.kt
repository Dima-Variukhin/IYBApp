package com.example.iybapp.core.data

interface CommonRepository<E> {
    suspend fun getCommonItem(): CommonDataModel<E>
    suspend fun changeStatus(): CommonDataModel<E>
    fun chooseDataSource(cached: Boolean)
}