package com.example.iybapp

class CloudResultHandler(
    private val cachedAction: CachedAction,
    jokeDataFetcher: ActionDataFetcher<ActionServerModel, ErrorType>,
    private val noConnection: ActionFailure,
    private val serviceUnavailable: ActionFailure
) :
    BaseResultHandler<ActionServerModel, ErrorType>(jokeDataFetcher) {
    override fun handleResult(result: Result<ActionServerModel, ErrorType>) = when (result) {
        is Result.Success<ActionServerModel> -> {
            result.data.toAction().let {
                cachedAction.saveAction(it)
                it.toBaseAction()
            }
        }
        is Result.Error<ErrorType> -> {
            cachedAction.clear()
            val failure =
                if (result.exception == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
            FailedActionUiModel(failure.getMessage())
        }
    }
}