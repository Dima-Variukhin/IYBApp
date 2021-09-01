package com.example.iybapp.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.example.iybapp.presentation.CommonUiModel

interface ListCommunication<T> {
    fun getList(): List<CommonUiModel<T>>
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel<T>>>)
    fun showDataList(list: List<CommonUiModel<T>>)
    fun getDiffResult(): DiffUtil.DiffResult

}