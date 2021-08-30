package com.example.iybapp.data.cache

import com.example.iybapp.core.data.ChangeCommonItem
import com.example.iybapp.core.data.ChangeStatus
import com.example.iybapp.core.data.CommonDataModel
import com.example.iybapp.core.data.cache.CachedData

class BaseCachedData : CachedData {
    private var cached: ChangeCommonItem = ChangeCommonItem.Empty()
    override fun save(data: CommonDataModel) {
        cached = data
    }

    override fun clear() {
        cached = ChangeCommonItem.Empty()
    }

    override suspend fun change(changeStatus: ChangeStatus): CommonDataModel {
        return cached.change(changeStatus)
    }
}