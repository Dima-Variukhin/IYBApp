package com.example.iybapp.core.data.cache

import com.example.iybapp.core.data.ChangeStatus
import com.example.iybapp.core.data.CommonDataModel
import com.example.iybapp.core.data.DataFetcher

interface CacheDataSource<E> : DataFetcher<E>, ChangeStatus<E> {
    override suspend fun getData(): CommonDataModel<E>

}