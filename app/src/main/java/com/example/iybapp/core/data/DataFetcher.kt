package com.example.iybapp.core.data


interface DataFetcher {
    suspend fun getData(): CommonDataModel
}