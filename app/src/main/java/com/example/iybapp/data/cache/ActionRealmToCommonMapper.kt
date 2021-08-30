package com.example.iybapp.data.cache

import com.example.iybapp.core.data.CommonDataModel
import com.example.iybapp.core.data.cache.RealmToCommonDataMapper

class ActionRealmToCommonMapper : RealmToCommonDataMapper<ActionRealmModel> {
    override fun map(realmObject: ActionRealmModel) =
        CommonDataModel(realmObject.key, 0, 0.0, realmObject.activity, realmObject.type, true)
}