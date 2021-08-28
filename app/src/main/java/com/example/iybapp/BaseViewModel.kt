package com.example.iybapp

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BaseViewModel(
    private val model: Model,
    private val communication: Communication,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    fun changeActionStatus() = viewModelScope.launch(dispatcher) {
        model.changeActionStatus()?.let {
            communication.showState(State.Progress)
        }
    }

    fun chooseFavorites(favorites: Boolean) = model.chooseDataSource(favorites)

    fun getAction() = viewModelScope.launch(dispatcher) {
        communication.showState(State.Progress)
        model.getAction().show(communication)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<State>) =
        communication.observe(owner, observer)

    sealed class State {
        abstract fun show(
            progress: ShowView,
            button: EnableView,
            textView: ShowText,
            imageButton: ShowImage
        )

        object Progress : State() {
            override fun show(
                progress: ShowView,
                button: EnableView,
                textView: ShowText,
                imageButton: ShowImage
            ) {
                progress.show(true)
                button.enable(false)
            }
        }

        data class Initial(val text: String, @DrawableRes val id: Int) : State() {
            override fun show(
                progress: ShowView,
                button: EnableView,
                textView: ShowText,
                imageButton: ShowImage
            ) {
                progress.show(false)
                button.enable(true)
                textView.show(text)
                imageButton.show(id)
            }
        }
    }

    interface ShowText {
        fun show(text: String)
    }

    interface ShowImage {
        fun show(@DrawableRes id: Int)
    }

    interface ShowView {
        fun show(show: Boolean)
    }

    interface EnableView {
        fun enable(enable: Boolean)
    }
}

