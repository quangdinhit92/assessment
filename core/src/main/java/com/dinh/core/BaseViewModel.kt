package com.dinh.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


sealed class UiState {
    data class Loading(val loading: Boolean) : UiState()
    data class Error(val message: String) : UiState()
    data class Message(val message: String) : UiState()
    object Idle : UiState()
}


interface BaseInput
interface BaseOutput


abstract class BaseViewModel(private val dispatcher: DispatcherProvider) : ViewModel() {

    private val _commonState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Idle)
    val commonState: StateFlow<UiState> get() = _commonState



    //  abstract val input: BaseInput
    abstract val output: BaseOutput

    val execeptionHandler = CoroutineExceptionHandler { _, throwable ->
        _commonState.value = UiState.Error(throwable.message ?: "Unknown error")
    }

    fun showError(errer: String) {
        _commonState.value = UiState.Error(errer)
    }


    protected fun CoroutineScope.launchWithoutException(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job = launch(context = context + execeptionHandler, start = start) {
        block.invoke(this)
    }
}