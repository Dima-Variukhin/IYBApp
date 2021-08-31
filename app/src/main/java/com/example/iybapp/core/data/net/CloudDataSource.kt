package com.example.iybapp.core.data.net

import com.example.iybapp.core.data.CommonDataModel
import com.example.iybapp.core.data.DataFetcher

interface CloudDataSource<E> : DataFetcher<E> {
    override suspend fun getData(): CommonDataModel<E>
}