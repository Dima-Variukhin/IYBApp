package com.example.iybapp

interface ChangeAction {
    suspend fun change(changeActionStatus: ChangeActionStatus) :ActionUiModel?
}