package com.example.iybapp.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.iybapp.presentation.CommonUiModel
import com.example.iybapp.presentation.State

interface CommonViewModel<T> : CommonItemViewModel {
    fun changeItemStatus(id: T): Int
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel<T>>>)}

interface CommonItemViewModel {
    fun getItem()
    fun getItemList()
    fun changeItemStatus()
    fun chooseFavorites(favorites: Boolean)
}
