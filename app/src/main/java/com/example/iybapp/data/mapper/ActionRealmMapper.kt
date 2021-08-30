package com.example.iybapp.data.mapper

import com.example.iybapp.CommonDataModelMapper
import com.example.iybapp.data.cache.ActionRealmModel

class ActionRealmMapper : CommonDataModelMapper<ActionRealmModel> {
    override fun map(
        key: String,
        third: Int,
        fourth: Double,
        first: String,
        second: String,
        cached: Boolean
    ): ActionRealmModel {
        return ActionRealmModel().also { action ->
            action.key = key
            action.participants = third
            action.accessibility = fourth
            action.activity = first
            action.type = second
        }
    }
}