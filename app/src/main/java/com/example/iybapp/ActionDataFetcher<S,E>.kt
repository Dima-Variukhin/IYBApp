package com.example.iybapp

interface ActionDataFetcher<S, E> {
    suspend fun getAction(): Result<S, E>
}