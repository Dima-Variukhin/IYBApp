package com.example.iybapp

import com.example.iybapp.core.Mapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ActionRealmModel : RealmObject(), Mapper<ActionDataModel> {
    @PrimaryKey
    var key: String = ""
    var participants: Int = 0
    var accessibility: Int = 0
    var activity: String = ""
    var type: String = ""

    override fun to() = ActionDataModel(key, participants, accessibility, activity, type, true)
}