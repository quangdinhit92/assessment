package com.dinh.feature.ui.screen.visitgardern

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinh.core.BaseOutput
import com.dinh.core.BaseViewModel
import com.dinh.core.DispatcherProvider
import com.dinh.feature.UiModel.UiModelSearch
import com.dinh.feature.navigation.AppDestination
import com.dinh.feature.ui.screen.home.Output
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class VisitGardenViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider), Output {



    protected val _navigator: MutableSharedFlow<AppDestination> = MutableSharedFlow()
    val navigator: MutableSharedFlow<AppDestination> get() = _navigator

    override val output: BaseOutput
        get() = TODO("Not yet implemented")
    override val listSeach: StateFlow<List<UiModelSearch>>
        get() = TODO("Not yet implemented")
    override val isLoading: StateFlow<Boolean>
        get() = TODO("Not yet implemented")


    fun onClickSelectCrop() {
        viewModelScope.launchWithoutException {
            _navigator.emit(AppDestination.SelectCrop)
        }
    }


}