package com.example.iybapp.core.data.cache

import com.example.iybapp.core.data.ChangeCommonItem
import com.example.iybapp.core.data.CommonDataModel


interface CachedData : ChangeCommonItem {
    fun save(data: CommonDataModel)
    fun clear()
}