package com.example.iybapp.core.data.net

import com.example.iybapp.core.data.CommonDataModel
import com.example.iybapp.core.data.DataFetcher

interface CloudDataSource : DataFetcher {
    override suspend fun getData(): CommonDataModel
}