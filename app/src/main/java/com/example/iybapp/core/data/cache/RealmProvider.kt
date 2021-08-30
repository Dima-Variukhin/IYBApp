package com.example.iybapp.core.data.cache

import io.realm.Realm

interface RealmProvider {
    fun provide(): Realm
}
