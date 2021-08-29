package com.example.iybapp

import com.example.iybapp.data.ActionUiModel
import java.lang.IllegalStateException

interface ChangeAction {
    suspend fun change(changeActionStatus: ChangeActionStatus): ActionDataModel

    class Empty : ChangeAction {
        override suspend fun change(changeActionStatus: ChangeActionStatus): ActionDataModel {
            throw IllegalStateException("empty change action called")
        }
    }
}