package com.example.iybapp.core.domain

import com.example.iybapp.domain.CommonItem


interface CommonInteractor<T> {

    suspend fun getItem(): CommonItem<T>
    suspend fun getItemList(): List<CommonItem<T>>

    suspend fun changeFavorites(): CommonItem<T>

    suspend fun removeItem(id: T)

    fun getFavorites(favorite: Boolean)
}