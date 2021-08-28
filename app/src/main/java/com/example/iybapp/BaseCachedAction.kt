package com.example.iybapp

class BaseCachedAction : CachedAction {
    private var cached: Action? = null
    override fun saveAction(action: Action) {
        cached = action
    }

    override fun clear() {
        cached = null
    }

    override suspend fun change(changeActionStatus: ChangeActionStatus): ActionUiModel? {
        return cached?.change(changeActionStatus)
    }
}