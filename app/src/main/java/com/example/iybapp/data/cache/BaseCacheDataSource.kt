package com.example.iybapp.data.cache

import com.example.iybapp.CommonDataModelMapper
import com.example.iybapp.core.data.CommonDataModel
import com.example.iybapp.core.data.cache.CacheDataSource
import com.example.iybapp.core.data.cache.RealmProvider
import com.example.iybapp.core.data.cache.RealmToCommonDataMapper
import com.example.iybapp.core.domain.NoCachedDataException
import com.example.iybapp.data.mapper.ActionRealmMapper
import com.example.iybapp.data.mapper.QuoteRealmMapper
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ActionCachedDataSource(
    realmProvider: RealmProvider,
    mapper: ActionRealmMapper,
    commonDataMapper: ActionRealmToCommonMapper
) : BaseCacheDataSource<ActionRealmModel, String>(realmProvider, mapper, commonDataMapper) {
    override val dbClass = ActionRealmModel::class.java
    override fun findRealmObject(realm: Realm, key: String) =
        realm.where(dbClass).equalTo("key", key).findFirst()
}

class QuoteCachedDataSource(
    realmProvider: RealmProvider,
    mapper: QuoteRealmMapper,
    commonDataMapper: QuoteRealmToCommonMapper
) :
    BaseCacheDataSource<QuoteRealmModel, String>(realmProvider, mapper, commonDataMapper) {
    override val dbClass = QuoteRealmModel::class.java
    override fun findRealmObject(realm: Realm, key: String) =
        realm.where(dbClass).equalTo("key", key).findFirst()
}

abstract class BaseCacheDataSource<T : RealmObject, E>(
    private val realmProvider: RealmProvider,
    private val mapper: CommonDataModelMapper<T, E>,
    private val realmToCommonDataMapper: RealmToCommonDataMapper<T, E>
) : CacheDataSource<E> {
    protected abstract val dbClass: Class<T>
    override suspend fun getData() = getRealmData {
        realmToCommonDataMapper.map(it.random())
    }

    override suspend fun getDataList() = getRealmData { results ->
        results.map { realmToCommonDataMapper.map(it) }
    }

    private fun <R> getRealmData(block: (list: RealmResults<T>) -> R): R {
        realmProvider.provide().use {
            val list = it.where(dbClass).findAll()
            if (list.isEmpty())
                throw NoCachedDataException()
            else
                return block.invoke(list)
        }
    }

    protected abstract fun findRealmObject(realm: Realm, key: E): T?

    override suspend fun addOrRemove(key: E, model: CommonDataModel<E>): CommonDataModel<E> =
        withContext(Dispatchers.IO) {
            realmProvider.provide().use {
                val itemRealm = findRealmObject(it, key)
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

    override suspend fun remove(id: E) = withContext(Dispatchers.IO) {
        realmProvider.provide().use { realm ->
            realm.executeTransaction {
                findRealmObject(realm, id)?.deleteFromRealm()
            }
        }
    }
}