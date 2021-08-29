package com.example.iybapp

import com.example.iybapp.data.ActionRepository
import com.example.iybapp.data.CacheDataSource
import com.example.iybapp.data.CloudDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class BaseActionRepository(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val cachedAction: CachedAction
) : ActionRepository {

    private var currentDataSource: ActionDataFetcher = cloudDataSource
    override fun chooseDataSource(cached: Boolean) {
        currentDataSource = if (cached) cacheDataSource else cloudDataSource
    }

    override suspend fun getAction(): ActionDataModel = withContext(Dispatchers.IO) {
        try {
            val action = currentDataSource.getAction()
            cachedAction.saveAction(action)
            return@withContext action
        } catch (e: Exception) {
            cachedAction.clear()
            throw e
        }
    }

    override suspend fun changeActionStatus(): ActionDataModel =
        cachedAction.change(cacheDataSource)
}