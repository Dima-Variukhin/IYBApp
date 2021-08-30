package com.example.iybapp.data

import com.example.iybapp.core.data.CommonDataModel
import com.example.iybapp.core.data.CommonRepository
import com.example.iybapp.core.data.DataFetcher
import com.example.iybapp.core.data.cache.CacheDataSource
import com.example.iybapp.core.data.cache.CachedData
import com.example.iybapp.core.data.net.CloudDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseRepository(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val cached: CachedData
) : CommonRepository {

    private var currentDataSource: DataFetcher = cloudDataSource
    override fun chooseDataSource(cached: Boolean) {
        currentDataSource = if (cached) cacheDataSource else cloudDataSource
    }

    override suspend fun getCommonItem(): CommonDataModel = withContext(Dispatchers.IO) {
        try {
            val data = currentDataSource.getData()
            cached.save(data)
            return@withContext data
        } catch (e: Exception) {
            cached.clear()
            throw e
        }
    }

    override suspend fun changeStatus(): CommonDataModel =
        cached.change(cacheDataSource)
}