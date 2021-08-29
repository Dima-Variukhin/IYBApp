package com.example.iybapp

import com.example.iybapp.domain.Action
import com.example.iybapp.data.ActionUiModel

class BaseCachedAction : CachedAction {
    private var cached: ChangeAction = ChangeAction.Empty()
    override fun saveAction(action: ActionDataModel) {
        cached = action
    }

    override fun clear() {
        cached = ChangeAction.Empty()
    }

    override suspend fun change(changeActionStatus: ChangeActionStatus): ActionDataModel {
        return cached.change(changeActionStatus)
    }
}