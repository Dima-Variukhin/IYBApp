package com.example.iybapp

abstract class BaseResultHandler<S, E>(
    private val actionDataFetcher: ActionDataFetcher<S, E>
) : ResultHandler<S, E> {
    suspend fun process(): ActionUiModel {
        return handleResult(actionDataFetcher.getAction())
    }

    abstract override fun handleResult(result: Result<S, E>): ActionUiModel
}