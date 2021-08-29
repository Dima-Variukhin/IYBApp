package com.example.iybapp

class ActionDataModel(
    private val key: String,
    private val participants: Int,
    private val accessibility: Int,
    val activity: String,
    val type: String,
    private val cached: Boolean = false
) : ChangeAction {

    fun <T> map(mapper: ActionDataModelMapper<T>): T {
        return mapper.map(key, participants, accessibility, activity, type, cached)
    }

    override suspend fun change(changeActionStatus: ChangeActionStatus) =
        changeActionStatus.addOrRemove(key, this)


    fun changeCached(cached: Boolean) =
        ActionDataModel(key, participants, accessibility, activity, type, cached)

}