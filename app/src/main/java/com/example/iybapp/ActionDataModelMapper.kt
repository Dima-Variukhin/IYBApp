package com.example.iybapp

import com.example.iybapp.domain.Action

interface ActionDataModelMapper<T> {
    fun map(
        key: String,
        participants: Int,
        accessibility: Int,
        activity: String,
        type: String,
        cached: Boolean
    ): T
}

class ActionSuccessMapper : ActionDataModelMapper<Action.Success> {
    override fun map(
        key: String,
        participants: Int,
        accessibility: Int,
        activity: String,
        type: String,
        cached: Boolean
    ) = Action.Success(activity, type, cached)

}

class ActionRealmMapper : ActionDataModelMapper<ActionRealmModel> {
    override fun map(
        key: String,
        participants: Int,
        accessibility: Int,
        activity: String,
        type: String,
        cached: Boolean
    ): ActionRealmModel {
        return ActionRealmModel().also { action ->
            action.key = key
            action.participants = participants
            action.accessibility = accessibility
            action.activity = activity
            action.type = type
        }
    }
}