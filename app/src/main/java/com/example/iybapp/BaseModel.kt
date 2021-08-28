package com.example.iybapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cacheResultHandler: CacheResultHandler,
    private val cloudResultHandler: CloudResultHandler,
    private val cachedAction: CachedAction
) : Model {
    private var currentResultHandler: BaseResultHandler<*, *> = cloudResultHandler
    override suspend fun getAction(): ActionUiModel = withContext(Dispatchers.IO) {
        return@withContext currentResultHandler.process()
    }

    override suspend fun changeActionStatus(): ActionUiModel? = cachedAction?.change(cacheDataSource)

    override fun chooseDataSource(cached: Boolean) {
        currentResultHandler = if (cached) cacheResultHandler else cloudResultHandler
    }
}