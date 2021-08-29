package com.example.iybapp.data

import com.example.iybapp.ActionDataFetcher
import com.example.iybapp.ActionDataModel
import com.example.iybapp.ChangeActionStatus

interface CacheDataSource : ActionDataFetcher, ChangeActionStatus {
    override suspend fun getAction(): ActionDataModel

}