package com.example.iybapp


interface ActionDataFetcher {
    suspend fun getAction(): ActionDataModel
}