package com.example.iybapp.data.cache

import com.example.iybapp.CommonDataModelMapper
import com.example.iybapp.core.data.CommonDataModel
import com.example.iybapp.core.data.cache.CacheDataSource
import com.example.iybapp.core.data.cache.RealmProvider
import com.example.iybapp.core.data.cache.RealmToCommonDataMapper
import com.example.iybapp.core.domain.NoCachedDataException
import com.example.iybapp.data.mapper.ActionRealmMapper
import com.example.iybapp.data.mapper.QuoteRealmMapper
import io.realm.RealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ActionCachedDataSource(
    realmProvider: RealmProvider,
    mapper: ActionRealmMapper,
    commonDataMapper: ActionRealmToCommonMapper
) : BaseCacheDataSource<ActionRealmModel>(realmProvider, mapper, commonDataMapper) {
    override val dbClass = ActionRealmModel::class.java
}

class QuoteCachedDataSource(
    realmProvider: RealmProvider,
    mapper: QuoteRealmMapper,
    commonDataMapper: QuoteRealmToCommonMapper
) :
    BaseCacheDataSource<QuoteRealmModel>(realmProvider, mapper, commonDataMapper) {
    override val dbClass = QuoteRealmModel::class.java
}

abstract class BaseCacheDataSource<T : RealmObject>(
    private val realmProvider: RealmProvider,
    private val mapper: CommonDataModelMapper<T>,
    private val realmToCommonDataMapper: RealmToCommonDataMapper<T>
) : CacheDataSource {
    protected abstract val dbClass: Class<T>
    override suspend fun getData(): CommonDataModel {
        realmProvider.provide().use {
            val list = it.where(dbClass).findAll()
            if (list.isEmpty())
                throw NoCachedDataException()
            else
                return realmToCommonDataMapper.map(list.random())
        }
    }

    override suspend fun addOrRemove(key: String, model: CommonDataModel): CommonDataModel =
        withContext(Dispatchers.IO) {
            realmProvider.provide().use {
                val itemRealm =
                    it.where(dbClass).equalTo("key", key).findFirst()
                return@withContext if (itemRealm == null) {
                    it.executeTransaction { transaction ->
                        val newData = model.map(mapper)
                        transaction.insert(newData)
                    }
                    model.changeCached(true)
                } else {
                    it.executeTransaction {
                        itemRealm.deleteFromRealm()
                    }
                    model.changeCached(false)
                }
            }
        }
}