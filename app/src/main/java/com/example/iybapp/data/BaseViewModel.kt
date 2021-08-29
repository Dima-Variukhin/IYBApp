package com.example.iybapp.data

import androidx.lifecycle.*
import com.example.iybapp.ActionInteractor
import com.example.iybapp.Communication
import com.example.iybapp.State
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BaseViewModel(
    private val interactor: ActionInteractor,
    private val communication: Communication,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    fun changeActionStatus() = viewModelScope.launch(dispatcher) {
        if (communication.isState(State.INITIAL))
            interactor.changeFavorites().to().show(communication)
    }

    fun chooseFavorites(favorites: Boolean) = interactor.getFavoriteActions(favorites)

    fun getAction() = viewModelScope.launch(dispatcher) {
        communication.showState(State.Progress)
        interactor.getAction().to().show(communication)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<State>) =
        communication.observe(owner, observer)
}