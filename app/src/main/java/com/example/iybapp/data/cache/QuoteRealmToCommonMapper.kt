package com.example.iybapp.data.cache

import com.example.iybapp.core.data.CommonDataModel
import com.example.iybapp.core.data.cache.RealmToCommonDataMapper

class QuoteRealmToCommonMapper : RealmToCommonDataMapper<QuoteRealmModel, String> {
    override fun map(realmObject: QuoteRealmModel) =
        CommonDataModel(realmObject.key, 0, 0.0, realmObject.content, realmObject.author, true)
}