package com.example.iybapp.data

import com.example.iybapp.ActionDataFetcher
import com.example.iybapp.ActionDataModel

interface CloudDataSource : ActionDataFetcher {
    override suspend fun getAction(): ActionDataModel
}