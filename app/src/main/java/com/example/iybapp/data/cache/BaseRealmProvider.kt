package com.example.iybapp.data.cache

import com.example.iybapp.core.data.cache.RealmProvider
import io.realm.Realm

class BaseRealmProvider : RealmProvider {
    override fun provide(): Realm = Realm.getDefaultInstance()
}