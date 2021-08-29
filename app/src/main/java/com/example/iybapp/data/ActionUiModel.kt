package com.example.iybapp.data

import androidx.annotation.DrawableRes
import com.example.iybapp.*

class BaseActionUiModel(activity: String, type: String) : ActionUiModel(activity, type) {
    override fun getIconResId() = R.drawable.baseline_favorite_border_black_24
}

class FavoriteActionUiModel(activity: String, type: String) : ActionUiModel(activity, type) {
    override fun getIconResId() = R.drawable.baseline_favorite_black_24
}

class FailedActionUiModel(activity: String) : ActionUiModel(activity, "") {
    override fun text() = activity
    override fun getIconResId() = 0
    override fun show(communication: Communication) = communication.showState(
        State.Failed(text(), getIconResId())
    )
}

abstract class ActionUiModel(val activity: String, private val type: String) {
    protected open fun text() = "$activity\nType: \"$type\""

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
