package com.dinh.feature.ui.screen.login

import com.dinh.core.BaseOutput
import com.dinh.core.BaseViewModel
import com.dinh.core.DispatcherProvider
import com.dinh.feature.UiModel.UiModelSearch
import com.dinh.feature.ui.screen.home.Output
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    override val output: BaseOutput,
    override val listSeach: StateFlow<List<UiModelSearch>>,
    override val isLoading: StateFlow<Boolean>
) : BaseViewModel(dispatcherProvider), Output {}