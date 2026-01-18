package com.dinh.feature.home

import app.cash.turbine.test
import com.dinh.core.UiState
import com.dinh.domain.usecases.SearchUsecase
import com.dinh.domain.util.ApiResult
import com.dinh.feature.BaseViewModelTest
import com.dinh.feature.StandardDispatcherRule
import com.dinh.feature.UiModel.toUiModel
import com.dinh.feature.test.MockUtil
import com.dinh.feature.ui.screen.home.HomeIntents
import com.dinh.feature.ui.screen.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(JUnit4::class)
class HomeViewModelTest : BaseViewModelTest() {

    val mockUsecase = mockk<SearchUsecase>()
    lateinit var viewModel: HomeViewModel

    @get:Rule
    val watcher = StandardDispatcherRule()

    @get:Rule
    val mokito: MockitoRule = MockitoJUnit.rule()

    @Test
    fun `test call usecase success then return ApiResult success `() = runTest {
        // Given
        val searchTerm = "test"
        val limit = 25
        val expectedResult = ApiResult.Success(MockUtil.mySearch)



        coEvery {
            mockUsecase.invoke(
                SearchUsecase.Input(
                    searchTerm, limit, 0
                )
            )
        } returns flowOf(expectedResult)

        viewModel = HomeViewModel(watcher.testDispatcherProvider, mockUsecase)

        // When
        viewModel.onHandleIntent(HomeIntents.SearchQuerry(searchTerm, limit))

        // Then
        // Verify search query is updated
        viewModel.searchQuerry.test {

            assert(awaitItem() == searchTerm)
            cancelAndConsumeRemainingEvents()
        }
//        viewModel.searchQuerry.test {
//            assert(awaitItem() == "") // Initial state
//            assert(awaitItem() == searchTerm) // After search
//            cancelAndConsumeRemainingEvents()
//        }
//
//        // Verify limit is updated
//        viewModel.limit.test {
//            assert(awaitItem() == 25) // Default limit
//            assert(awaitItem() == limit) // After search
//            cancelAndConsumeRemainingEvents()
//        }
//
//        // Verify current page is reset
//        viewModel.currentPage.test {
//            assert(awaitItem() == 1) // Initial state
//            assert(awaitItem() == 0) // Reset after search
//            assert(awaitItem() == 1) // Increment after successful search
//            cancelAndConsumeRemainingEvents()
//        }
//
//        // Verify loading state
//        viewModel.output.isLoading.test {
//            assert(awaitItem() == false) // Initial state
//            assert(awaitItem() == true) // Loading starts
//            assert(awaitItem() == false) // Loading ends
//            cancelAndConsumeRemainingEvents()
//        }

        // Verify search results
        viewModel.output.listSeach.test {
            //  assert(awaitItem() == emptyList()) // Initial state
            val tmp = awaitItem()
            assert(0 == tmp.size) // After search
            val tmp1 = awaitItem()
            assert(tmp1 == expectedResult.data.map { it.toUiModel() }) // After search
            cancelAndConsumeRemainingEvents()
        }
    }


    @Test
    fun `test call usecase fail then return ApiResult error`() = runTest {

        val searchTerm = "help"
        val limit = 25
        val errorMessage = "Custom errorMessage"
        val errorCode = 403
        val expectedError = ApiResult.Error(errorCode, errorMessage)

        coEvery {
            mockUsecase.invoke(
                SearchUsecase.Input(
                    searchTerm,
                    limit,
                    page = 0
                )
            )
        } returns flowOf(expectedError)

        viewModel=HomeViewModel(watcher.testDispatcherProvider,mockUsecase)

        viewModel.onHandleIntent(HomeIntents.SearchQuerry(searchTerm, limit))

        viewModel.output.listSeach.test {
            assert(0 == awaitItem().size)
            cancelAndConsumeRemainingEvents()
        }
        viewModel.commonState.test {
            val result=awaitItem()
            assert((result as UiState.Error).message == expectedError.message)
            cancelAndConsumeRemainingEvents()
        }


    }

//    @Test
//    fun `test call usecase fail then return ApiResult error`() = runTest {
//        // Given
//        val searchTerm = "test"
//        val limit = 25
//        val errorMessage = "Error occurred"
//        val expectedResult = ApiResult.Error(errorMessage)
//
//        coEvery {
//            mockUsecase.invoke(
//                SearchUsecase.Input(
//                    searchTerm, limit, 0
//                )
//            )
//        } returns flowOf(expectedResult)
//
//        viewModel = HomeViewModel(watcher.testDispatcherProvider, mockUsecase)
//
//        // When
//        viewModel.onHandleAction(Actions.onSearch(searchTerm, limit))
//
//        // Then
//        // Verify loading state
//        viewModel.output.isLoading.test {
//            assert(awaitItem() == false) // Initial state
//            assert(awaitItem() == true) // Loading starts
//            assert(awaitItem() == false) // Loading ends
//            cancelAndConsumeRemainingEvents()
//        }
//
//        // Verify search results remain empty
//        viewModel.output.listSeach.test {
//            assert(awaitItem() == emptyList()) // Initial state
//            assert(awaitItem() == emptyList()) // After error
//            cancelAndConsumeRemainingEvents()
//        }
//
//        // Verify current page is not incremented
//        viewModel.currentPage.test {
//            assert(awaitItem() == 1) // Initial state
//            assert(awaitItem() == 0) // Reset after search
//            cancelAndConsumeRemainingEvents()
//        }
//    }
//
//    @Test
//    fun `click on item then navigate to detail scree`() = runTest {
//        // TODO: Implement navigation test
//    }
}