package com.ysfcyln.mviplayground.ui

import androidx.lifecycle.viewModelScope
import com.ysfcyln.mviplayground.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {


    /**
     * Create initial State of Views
     */
    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            MainContract.RandomNumberState.Idle
        )
    }

    /**
     * Handle each event
     */
    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnRandomNumberClicked -> { generateRandomNumber() }
            is MainContract.Event.OnShowToastClicked -> {
                setEffect { MainContract.Effect.ShowToast }
            }
        }
    }


    /**
     * Generate a random number
     */
    private fun generateRandomNumber() {
        viewModelScope.launch {
            // Set Loading
            setState { copy(randomNumberState = MainContract.RandomNumberState.Loading) }
            try {
                delay(5000)
                val random = (0..10).random()
                if (random % 2 == 0) {
                    setState { copy(randomNumberState = MainContract.RandomNumberState.Idle) }
                    throw RuntimeException("Number is even")
                }
                setState { copy(randomNumberState = MainContract.RandomNumberState.Success(number = random)) }
            } catch (exception : Exception) {
                setEffect { MainContract.Effect.ShowToast }
            }
        }
    }
}