package com.example.iybapp.presentation

import androidx.annotation.DrawableRes
import com.example.iybapp.CommonDataRecyclerAdapter
import com.example.iybapp.R
import com.example.iybapp.core.presentation.Communication
import com.example.iybapp.core.presentation.ShowText


class BaseCommonUiModel<E>(activity: String, type: String) : CommonUiModel<E>(activity, type) {
    override fun getIconResId() = R.drawable.baseline_favorite_border_black_24
}

class FavoriteCommonUiModel<E>(private val key: E, activity: String, type: String) :
    CommonUiModel<E>(activity, type) {
    override fun change(listener: CommonDataRecyclerAdapter.FavoriteItemClickListener<E>) =
        listener.change(key)

    override fun getIconResId() = R.drawable.baseline_favorite_black_24

    override fun matches(id: E): Boolean = this.key == key

    override fun same(model: CommonUiModel<E>): Boolean {
        return model is FavoriteCommonUiModel<E> && model.key == key
    }
}

class FailedCommonUiModel<E>(activity: String) : CommonUiModel<E>(activity, "") {
    override fun text() = first
    override fun getIconResId() = 0
    override fun show(communication: Communication) = communication.showState(
        State.Failed(text(), getIconResId())
    )
}

abstract class CommonUiModel<T>(val first: String, private val second: String) {
    protected open fun text() = "$first\nType: \"$second\""

    open fun same(model: CommonUiModel<T>): Boolean = false

    @DrawableRes
    abstract fun getIconResId(): Int

    open fun show(communication: Communication) = communication.showState(
        State.Initial(text(), getIconResId())
    )

    open fun change(listener: CommonDataRecyclerAdapter.FavoriteItemClickListener<T>) = Unit
    open fun matches(id: T): Boolean = false

    fun show(showText: ShowText) = showText.show(text())


}


