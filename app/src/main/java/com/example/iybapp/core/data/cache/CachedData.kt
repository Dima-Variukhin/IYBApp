package com.example.iybapp.core.data.cache

import com.example.iybapp.core.data.ChangeCommonItem
import com.example.iybapp.core.data.CommonDataModel


interface CachedData<E> : ChangeCommonItem<E> {
    fun save(data: CommonDataModel<E>)
    fun clear()
}