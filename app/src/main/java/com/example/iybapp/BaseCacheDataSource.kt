package com.example.iybapp

import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseCacheDataSource(private val realmProvider: RealmProvider) : CacheDataSource {
    override suspend fun getAction(): Result<Action, Unit> {
        realmProvider.provide().use {
            val actions = it.where(ActionRealm::class.java).findAll()
            if (actions.isEmpty())
                return Result.Error(Unit)
            else
                actions.random().let { action ->
                    return Result.Success(
                        Action(
                            action.key,
                            action.participants,
                            action.accessibility,
                            action.activity,
                            action.type
                        )
                    )
                }
        }
    }

    override suspend fun addOrRemove(key: String, action: Action): ActionUiModel =
        withContext(Dispatchers.IO) {
            Realm.getDefaultInstance().use {
                val actionRealm = it.where(ActionRealm::class.java).equalTo("key", key).findFirst()
                return@withContext if (actionRealm == null) {
                    it.executeTransaction { transaction ->
                        val newAction = action.toActionRealm()
                        transaction.insert(newAction)
                    }
                    action.toFavoriteAction()
                } else {
                    it.executeTransaction {
                        actionRealm.deleteFromRealm()
                    }
                    action.toBaseAction()
                }
            }
        }
}