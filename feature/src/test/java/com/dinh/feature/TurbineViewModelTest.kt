package com.dinh.feature

import app.cash.turbine.test
import com.dinh.feature.fortestingpurpose.ExampleViewModel
import com.dinh.feature.fortestingpurpose.HeavyComputationTemplate
import com.dinh.feature.fortestingpurpose.VmEvents
import com.dinh.feature.fortestingpurpose.VmState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.stub

@RunWith(JUnit4::class)
class TurbineViewModelTest {



    @Mock
    private lateinit var heavyComputation: HeavyComputationTemplate

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val standardDispatcherRule = StandardDispatcherRule()
    @Test
    fun `Given the sut is initialized, then it waits for event`() {
        val sut = ExampleViewModel(heavyComputation)

        assert(sut.vmState.value == VmState.Waiting)

    }

    @Test
    fun `Given the ViewModel waits - When the event OnLaunch comes, then execute heavy computation with result`() =
        runTest {
            val expectedString = "Result"
            heavyComputation.stub {
                onBlocking { doComputation() } doAnswer { expectedString }
            }

            val sut = ExampleViewModel(heavyComputation)

            sut.vmState.test {
                sut.onEvent(VmEvents.OnLaunch)

                assert(VmState.Waiting == awaitItem())
                assert(VmState.Running == awaitItem())
                assert(VmState.Finished(expectedString) == awaitItem())
                assert(VmState.Waiting == awaitItem())
            }


        }
}