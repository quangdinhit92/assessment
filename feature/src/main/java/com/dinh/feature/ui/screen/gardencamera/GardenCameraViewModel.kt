package com.dinh.feature.ui.screen.gardencamera

import androidx.lifecycle.ViewModel
import com.dinh.core.BaseOutput
import com.dinh.core.BaseViewModel
import com.dinh.core.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


interface Output : BaseOutput {

}

@HiltViewModel
class GardenCameraViewModel @Inject constructor(private val dispatcherProvider: DispatcherProvider) :
    BaseViewModel(dispatcherProvider), Output {
    override val output: BaseOutput
        get() = this
}