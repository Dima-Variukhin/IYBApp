package com.example.iybapp.domain

import com.example.iybapp.ActionFailure
import com.example.iybapp.core.Mapper
import com.example.iybapp.data.ActionUiModel
import com.example.iybapp.data.BaseActionUiModel
import com.example.iybapp.data.FailedActionUiModel
import com.example.iybapp.data.FavoriteActionUiModel

sealed class Action : Mapper<ActionUiModel> {
    class Success(
        val activity: String,
        val type: String,
        private val favorite: Boolean
    ) : Action() {
        override fun to(): ActionUiModel {
            return if (favorite) {
                FavoriteActionUiModel(activity, type)
            } else {
                BaseActionUiModel(activity, type)
            }
        }
    }

    class Failed(private val failure: ActionFailure) : Action() {
        override fun to(): ActionUiModel {
            return FailedActionUiModel(failure.getMessage())
        }
    }
}
