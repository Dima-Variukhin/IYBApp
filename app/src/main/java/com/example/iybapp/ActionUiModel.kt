package com.example.iybapp

import androidx.annotation.DrawableRes

class BaseActionUiModel(activity: String, type: String) : ActionUiModel(activity, type) {
    override fun getIconResId() = R.drawable.baseline_favorite_border_black_24
}

class FavoriteActionUiModel(activity: String, type: String) : ActionUiModel(activity, type) {
    override fun getIconResId() = R.drawable.baseline_favorite_black_24
}

class FailedActionUiModel(activity: String) : ActionUiModel(activity, "") {
    override fun text() = activity
    override fun getIconResId() = 0
}

abstract class ActionUiModel(val activity: String, private val type: String) {
    protected open fun text() = "$activity\nType: \"$type\""

    @DrawableRes
    abstract fun getIconResId(): Int

    fun getData() = Pair(text(), getIconResId())

    fun show(communication: Communication) =
        communication.showState(BaseViewModel.State.Initial(text(), getIconResId()))

//    fun map(callback: DataCallback) = callback.run {
//        provideText(text())
//        provideIconRes(getIconResId())
//    }
}
