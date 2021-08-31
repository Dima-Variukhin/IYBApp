package com.example.iybapp.core.data


interface DataFetcher<E> {
    suspend fun getData(): CommonDataModel<E>
}