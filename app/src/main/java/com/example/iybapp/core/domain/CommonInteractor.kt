package com.example.iybapp.core.domain

import com.example.iybapp.domain.CommonItem


interface CommonInteractor {

    suspend fun getItem(): CommonItem

    suspend fun changeFavorites(): CommonItem

    fun getFavorites(favorite: Boolean)
}