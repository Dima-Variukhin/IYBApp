package com.example.iybapp.presentation

import androidx.annotation.DrawableRes
import com.example.iybapp.R
import com.example.iybapp.core.presentation.Communication

class BaseCommonUiModel(activity: String, type: String) : CommonUiModel(activity, type) {
    override fun getIconResId() = R.drawable.baseline_favorite_border_black_24
}

class FavoriteCommonUiModel(activity: String, type: String) : CommonUiModel(activity, type) {
    override fun getIconResId() = R.drawable.baseline_favorite_black_24
}

class FailedCommonUiModel(activity: String) : CommonUiModel(activity, "") {
    override fun text() = first
    override fun getIconResId() = 0
    override fun show(communication: Communication) = communication.showState(
        State.Failed(text(), getIconResId())
    )
}

abstract class CommonUiModel(val first: String, private val second: String) {
    protected open fun text() = "$first\nType: \"$second\""

    @DrawableRes
    abstract fun getIconResId(): Int

    open fun show(communication: Communication) = communication.showState(
        State.Initial(text(), getIconResId())
    )

//    fun map(callback: DataCallback) = callback.run {
//        provideText(text())
//        provideIconRes(getIconResId())
//    }
}
