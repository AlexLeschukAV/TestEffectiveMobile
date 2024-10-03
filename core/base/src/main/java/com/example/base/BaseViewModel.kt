package com.example.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : MviState, A : MviAction, E : MviEffect> : ViewModel() {

    private val initialState: S by lazy { initialState() }

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state get() = _state.asStateFlow()

    private val _action: MutableSharedFlow<A> = MutableSharedFlow()
    val action get() = _action.asSharedFlow()

    private val _effect: Channel<E> = Channel()
    val effect get() = _effect.receiveAsFlow()

    val currentState: S get() = state.value

    init {
        subscribeEvents()
    }

    abstract fun initialState(): S

    protected abstract fun handleEvent(action: A)

    fun setAction(action: A) {
        viewModelScope.launch { _action.emit(action) }
    }

    protected fun setState(reduce: (S) -> S) {
        _state.update { reduce(it) }
    }

    protected fun setEffect(builder: () -> E) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    private fun subscribeEvents() {
        viewModelScope.launch {
            action.collect {
                handleEvent(it)
            }
        }
    }
}