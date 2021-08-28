package com.example.iybapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Communication {

    fun showState(state: BaseViewModel.State.Initial)

    fun observe(owner: LifecycleOwner, observer: Observer<BaseViewModel.State>)
}

class BaseCommunication : Communication {

    private val liveData = MutableLiveData<BaseViewModel.State>()

    override fun showState(state: BaseViewModel.State.Initial) {
        liveData.value = state
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<BaseViewModel.State>) {
        liveData.observe(owner, observer)
    }
}