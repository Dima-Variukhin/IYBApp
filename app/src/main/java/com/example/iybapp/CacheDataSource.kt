package com.example.iybapp

interface CacheDataSource : ActionDataFetcher<Action, Unit>, ChangeActionStatus {
    override suspend fun getAction(): Result<Action, Unit>

}