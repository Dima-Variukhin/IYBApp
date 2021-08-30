package com.example.iybapp.core.domain

import com.example.iybapp.core.presentation.Failure

interface FailureHandler {
    fun handle(e: Exception): Failure
}