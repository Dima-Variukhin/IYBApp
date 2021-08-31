package com.example.iybapp.data.cache

import com.example.iybapp.core.data.ChangeCommonItem
import com.example.iybapp.core.data.ChangeStatus
import com.example.iybapp.core.data.CommonDataModel
import com.example.iybapp.core.data.cache.CachedData

class BaseCachedData<E> : CachedData<E> {
    private var cached: ChangeCommonItem<E> = ChangeCommonItem.Empty()
    override fun save(data: CommonDataModel<E>) {
        cached = data
    }

    override fun clear() {
        cached = ChangeCommonItem.Empty()
    }

    override suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E> {
        return cached.change(changeStatus)
    }
}