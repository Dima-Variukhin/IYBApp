package com.example.iybapp.data.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ActionRealmModel : RealmObject() {
    @PrimaryKey
    var key: String = ""
    var participants: Int = 0
    var accessibility: Double = 0.0
    var activity: String = ""
    var type: String = ""

   // override fun to() = CommonDataModel(key, participants, accessibility, activity, type, true)
}