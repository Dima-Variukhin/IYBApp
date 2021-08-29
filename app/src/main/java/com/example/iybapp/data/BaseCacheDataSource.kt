package com.example.iybapp.data

import com.example.iybapp.*
import com.example.iybapp.domain.Action
import com.example.iybapp.domain.NoCachedActionsException
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseCacheDataSource(
    private val realmProvider: RealmProvider,
    private val mapper: ActionDataModelMapper<ActionRealmModel>
) : CacheDataSource {
    override suspend fun getAction(): ActionDataModel {
        realmProvider.provide().use {
            val actions = it.where(ActionRealmModel::class.java).findAll()
            if (actions.isEmpty())
                throw NoCachedActionsException()
            else
                return actions.random().to()

        }
    }

    override suspend fun addOrRemove(key: String, action: ActionDataModel): ActionDataModel =
        withContext(Dispatchers.IO) {
            realmProvider.provide().use {
                val actionRealm =
                    it.where(ActionRealmModel::class.java).equalTo("key", key).findFirst()
                return@withContext if (actionRealm == null) {
                    it.executeTransaction { transaction ->
                        val newAction = action.map(mapper)
                        transaction.insert(newAction)
                    }
                    action.changeCached(true)
                } else {
                    it.executeTransaction {
                        actionRealm.deleteFromRealm()
                    }
                    action.changeCached(false)
                }
            }
        }
}