package com.dinh.feature.ui.screen.selectcrop

import com.dinh.core.BaseOutput
import com.dinh.core.BaseViewModel
import com.dinh.core.DispatcherProvider
import com.dinh.domain.entities.CropEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


interface Output : BaseOutput {
    val crop: StateFlow<List<CropEntity>>
}

@HiltViewModel
class SelectCropViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider), Output {

    override val output: Output
        get() = this

    private val _crops: MutableStateFlow<List<CropEntity>> = MutableStateFlow(emptyList())

    override val crop: StateFlow<List<CropEntity>>
        get() = _crops


    init {
        _crops.update {
            listOf( CropEntity("1", "1", "1"),
                CropEntity("1", "1", "1"),
                CropEntity("1", "1", "1"),)
        }

    }
}