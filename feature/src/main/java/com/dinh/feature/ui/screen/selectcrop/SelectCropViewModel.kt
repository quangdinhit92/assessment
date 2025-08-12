package com.dinh.feature.ui.screen.selectcrop

import com.dinh.core.BaseOutput
import com.dinh.core.BaseViewModel
import com.dinh.core.DispatcherProvider
import com.dinh.domain.usecases.SearchUsecase
import com.dinh.feature.UiModel.UiModelSearch
import com.dinh.feature.ui.screen.home.Output
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class SelectCropViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider), Output {
    override val output: BaseOutput
        get() = TODO("Not yet implemented")
    override val listSeach: StateFlow<List<UiModelSearch>>
        get() = TODO("Not yet implemented")
    override val isLoading: StateFlow<Boolean>
        get() = TODO("Not yet implemented")

}