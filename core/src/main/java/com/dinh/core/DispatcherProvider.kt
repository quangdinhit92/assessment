package com.dinh.core

import javax.inject.Inject

interface DispatcherProvider {
    val io: kotlinx.coroutines.CoroutineDispatcher
    val main: kotlinx.coroutines.CoroutineDispatcher
    val default: kotlinx.coroutines.CoroutineDispatcher
}

class DispatcherProviderImpl @Inject constructor(): DispatcherProvider {
    override val io: kotlinx.coroutines.CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO
    override val main: kotlinx.coroutines.CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main
    override val default: kotlinx.coroutines.CoroutineDispatcher =
        kotlinx.coroutines.Dispatchers.Default
}
