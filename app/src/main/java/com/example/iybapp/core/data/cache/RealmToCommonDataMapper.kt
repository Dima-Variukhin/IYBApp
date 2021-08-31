package com.example.iybapp.core.data.cache

import com.example.iybapp.core.data.CommonDataModel
import io.realm.RealmObject

interface RealmToCommonDataMapper<T : RealmObject, E> {
    fun map(realmObject: T): CommonDataModel<E>
}
