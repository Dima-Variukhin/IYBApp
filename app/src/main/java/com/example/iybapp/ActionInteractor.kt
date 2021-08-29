package com.example.iybapp

import com.example.iybapp.domain.Action


interface ActionInteractor {

    suspend fun getAction(): Action

    suspend fun changeFavorites(): Action

    fun getFavoriteActions(favorite: Boolean)
}