package com.example.iybapp

interface ChangeActionStatus {
    suspend fun addOrRemove(key: String, action: Action): ActionUiModel
}