package com.example.iybapp.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.iybapp.presentation.State

interface Communication {

    fun showState(state: State)

    fun observe(owner: LifecycleOwner, observer: Observer<State>)

    fun isState(type: Int): Boolean
}

