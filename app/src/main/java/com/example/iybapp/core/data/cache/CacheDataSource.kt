package com.example.iybapp.core.data.cache

import com.example.iybapp.core.data.ChangeStatus
import com.example.iybapp.core.data.CommonDataModel
import com.example.iybapp.core.data.DataFetcher

interface CacheDataSource<E> : DataFetcher<E>, ChangeStatus<E> {
    suspend fun getDataList(): List<CommonDataModel<E>>
    suspend fun remove(id: E)
}