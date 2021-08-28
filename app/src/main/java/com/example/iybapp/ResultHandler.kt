package com.example.iybapp

interface ResultHandler<S, E> {
    fun handleResult(result: Result<S, E>): ActionUiModel
}