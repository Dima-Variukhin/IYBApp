package com.example.iybapp.domain

import com.example.iybapp.core.data.Mapper
import com.example.iybapp.core.presentation.Failure
import com.example.iybapp.presentation.BaseCommonUiModel
import com.example.iybapp.presentation.CommonUiModel
import com.example.iybapp.presentation.FailedCommonUiModel
import com.example.iybapp.presentation.FavoriteCommonUiModel

sealed class CommonItem : Mapper<CommonUiModel> {
    class Success(
        private val firstText: String,
        private val secondText: String,
        private val favorite: Boolean
    ) : CommonItem() {
        override fun to(): CommonUiModel {
            return if (favorite) {
                FavoriteCommonUiModel(firstText, secondText)
            } else {
                BaseCommonUiModel(firstText, secondText)
            }
        }
    }

    class Failed(private val failure: Failure) : CommonItem() {
        override fun to(): CommonUiModel {
            return FailedCommonUiModel(failure.getMessage())
        }
    }
}
