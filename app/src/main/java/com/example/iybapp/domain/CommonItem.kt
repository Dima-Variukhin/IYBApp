package com.example.iybapp.domain

import com.example.iybapp.core.data.Mapper
import com.example.iybapp.core.presentation.Failure
import com.example.iybapp.presentation.BaseCommonUiModel
import com.example.iybapp.presentation.CommonUiModel
import com.example.iybapp.presentation.FailedCommonUiModel
import com.example.iybapp.presentation.FavoriteCommonUiModel

sealed class CommonItem<E> : Mapper<CommonUiModel<E>> {
    class Success<E>(
        private val key: E,
        private val firstText: String,
        private val secondText: String,
        private val favorite: Boolean
    ) : CommonItem<E>() {
        override fun to() = if (favorite) {
            FavoriteCommonUiModel(key, firstText, secondText)
        } else {
            BaseCommonUiModel(firstText, secondText)
        }
    }

    class Failed<E>(private val failure: Failure) : CommonItem<E>() {
        override fun to(): CommonUiModel<E> = FailedCommonUiModel(failure.getMessage())
    }
}
