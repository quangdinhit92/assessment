package com.dinh.feature

import com.dinh.core.DispatcherProvider
import com.dinh.feature.test.CoroutineTestRule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Rule


abstract class BaseViewModelTest {
    @get:Rule
    private var coroutine = CoroutineTestRule()

    protected fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
        coroutine.runBlockingTest(block)

    protected val testDispatcher = coroutine.testDispatcher
    protected val testDispatcherProvider = object :
        DispatcherProvider {
        override val io: CoroutineDispatcher
            get() = testDispatcher
        override val main: CoroutineDispatcher
            get() = testDispatcher
        override val default: CoroutineDispatcher
            get() = testDispatcher

    }


}