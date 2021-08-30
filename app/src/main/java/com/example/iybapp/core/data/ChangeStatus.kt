package com.example.iybapp.core.data

interface ChangeStatus {
    suspend fun addOrRemove(key: String, model: CommonDataModel): CommonDataModel
}