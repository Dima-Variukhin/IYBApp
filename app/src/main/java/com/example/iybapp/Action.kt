package com.example.iybapp

class Action(
    private val key: String,
    private val participants: Int,
    private val accessibility: Int,
    private val activity: String,
    private val type: String
) : ChangeAction {
    fun toAction() = Action(key, participants, accessibility, activity, type)
    fun toBaseAction() = BaseActionUiModel(activity, type)

    override suspend fun change(changeActionStatus: ChangeActionStatus) =
        changeActionStatus.addOrRemove(key, this)

    fun toFavoriteAction() = FavoriteActionUiModel(activity, type)
    fun toActionRealm(): ActionRealm {
        return ActionRealm().also {
            it.key = key
            it.participants = participants
            it.accessibility = accessibility
            it.activity = activity
            it.type = type
        }
    }
}