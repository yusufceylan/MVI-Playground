package com.ysfcyln.mviplayground.ui

import com.ysfcyln.mviplayground.base.UiEffect
import com.ysfcyln.mviplayground.base.UiEvent
import com.ysfcyln.mviplayground.base.UiState

/**
 * Contract of [MainActivity]
 *
 * See if you want to another approach for create copy of sealed class
 *
 * https://ivanmorgillo.com/2020/10/28/how-to-fix-the-pain-of-modifying-kotlin-nested-data-classes/
 * https://ivanmorgillo.com/2020/11/19/doh-there-is-no-copy-method-for-sealed-classes-in-kotlin/
 */
class MainContract {

    sealed class Event : UiEvent {
        object OnRandomNumberClicked : Event()
        object OnShowToastClicked : Event()
    }

    data class State(
        val randomNumberState: RandomNumberState
    ) : UiState

    sealed class RandomNumberState {
        object Idle : RandomNumberState()
        object Loading : RandomNumberState()
        data class Success(val number : Int) : RandomNumberState()
    }

    sealed class Effect : UiEffect {

        object ShowToast : Effect()

    }

}