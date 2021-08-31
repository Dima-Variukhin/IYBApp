package com.example.iybapp.core.data

interface ChangeCommonItem<E> {
    suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E>

    class Empty<E> : ChangeCommonItem<E> {
        override suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E> {
            throw IllegalStateException("empty change action called")
        }
    }
}