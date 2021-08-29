package com.example.iybapp

import com.example.iybapp.domain.Action
import com.example.iybapp.data.ActionUiModel

interface ChangeActionStatus {
    suspend fun addOrRemove(key: String, action: ActionDataModel): ActionDataModel
}