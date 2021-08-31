package com.example.iybapp.core.data

interface ChangeStatus<E> {
    suspend fun addOrRemove(key: E, model: CommonDataModel<E>): CommonDataModel<E>
}