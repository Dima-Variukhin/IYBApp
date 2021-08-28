package com.example.iybapp

interface CachedAction : ChangeAction {
    fun saveAction(action: Action)
    fun clear()
}