package com.example.iybapp

interface CloudDataSource : ActionDataFetcher<ActionServerModel, ErrorType> {
    override suspend fun getAction(): Result<ActionServerModel, ErrorType>
}