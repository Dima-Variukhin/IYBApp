package com.example.iybapp

class CacheResultHandler(
    private val cachedAction: CachedAction,
    actionDataFetcher: ActionDataFetcher<Action, Unit>,
    private val noCachedActions: ActionFailure
) :
    BaseResultHandler<Action, Unit>(actionDataFetcher) {
    override fun handleResult(result: Result<Action, Unit>) = when (result) {
        is Result.Success<Action> -> result.data.let {
            cachedAction.saveAction(it)
            it.toFavoriteAction()
        }
        is Result.Error -> {
            cachedAction.clear()
            FailedActionUiModel(noCachedActions.getMessage())
        }
    }
}