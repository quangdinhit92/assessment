package com.dinh.feature

import com.dinh.core.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class StandardDispatcherRule(private val testDispatcher: TestDispatcher = StandardTestDispatcher()) :
    TestWatcher() {

    override fun starting(description: Description?) {

        Dispatchers.setMain(testDispatcher)
        super.starting(description)
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
        super.finished(description)
    }

    val testDispatcherProvider = object :DispatcherProvider{
        override val io: CoroutineDispatcher
            get() = TODO("Not yet implemented")
        override val main: CoroutineDispatcher
            get() = TODO("Not yet implemented")
        override val default: CoroutineDispatcher
            get() = TODO("Not yet implemented")

    }
}