package com.dinh.feature.ui.screen.login

import com.dinh.core.BaseOutput
import com.dinh.core.BaseViewModel
import com.dinh.core.DispatcherProvider
import com.dinh.feature.UiModel.UiModelSearch

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


interface Output: BaseOutput
{

}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
) : BaseViewModel(dispatcherProvider),Output {
    override val output: BaseOutput
        get() = this

}