package com.example.iybapp


interface CachedAction : ChangeAction {
    fun saveAction(action: ActionDataModel)
    fun clear()
}