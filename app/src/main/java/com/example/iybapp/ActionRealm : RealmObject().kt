package com.example.iybapp

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ActionRealm : RealmObject() {
    @PrimaryKey
    var key: String = ""
    var participants: Int = 0
    var accessibility: Int = 0
    var activity: String = ""
    var type: String = ""
}