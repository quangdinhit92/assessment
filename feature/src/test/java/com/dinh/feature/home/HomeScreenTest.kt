package com.dinh.feature.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.dinh.domain.usecases.SearchUsecase
import com.dinh.domain.util.ApiResult
import com.dinh.feature.BaseViewModelTest
import com.dinh.feature.StandardDispatcherRule
import com.dinh.feature.navigation.AppDestination
import com.dinh.feature.test.MockUtil
import com.dinh.feature.ui.screen.home.HomeIntents
import com.dinh.feature.ui.screen.home.HomeScreen
import com.dinh.feature.ui.screen.home.HomeViewModel
import com.dinh.feature.ui.screen.home.loadingTestTag
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HomeScreenTest : BaseViewModelTest() {

    @get:Rule
    val watcher = StandardDispatcherRule()

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockUsecase = mockk<SearchUsecase>()
    private var homeViewModel: HomeViewModel = mockk<HomeViewModel>()
    private var appDestination: AppDestination = mockk<AppDestination>()

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(watcher.testDispatcherProvider, mockUsecase)
    }

    @Test
    fun `loading indicator should show when searching`() {
        // Given
//        val searchTerm = "test"
//        val limit = 25
//        val expectedResult = ApiResult.Success(MockUtil.mySearch)
//
//        coEvery {
//            mockUsecase.invoke(
//                SearchUsecase.Input(
//                    searchTerm, limit, 0
//                )
//            )
//        } returns flowOf(expectedResult)
//
        // Set up the compose content
        composeTestRule.setContent {
            HomeScreen(viewModel = homeViewModel, onNavigate = { destination ->
                appDestination = destination
            })
        }
//
//        // Initially loading should not be visible
        composeTestRule.onNodeWithTag(testTag = loadingTestTag).assertDoesNotExist()
//
//        // When - Trigger search
//        homeViewModel.onHandleAction(Actions.onSearch(searchTerm, limit))
//
//        // Then - Loading should be visible during search
//        composeTestRule.onNodeWithTag(testTag = loadingTestTag).assertIsDisplayed()
    }

    @Test
    fun `loading indicator should hide after search completes`() {
        composeTestRule.setContent {
            HomeScreen(homeViewModel, { navigate -> appDestination = navigate })

        }
        val result = ApiResult.Success(MockUtil.mySearch)
        coEvery {
            mockUsecase.invoke(
                any()
            )
        } returns flowOf(result)
        homeViewModel.onHandleIntent(HomeIntents.SearchQuerry("searchTerm", 5))
        composeTestRule.onNodeWithTag(loadingTestTag).assertIsDisplayed()
        // Given
//        val searchTerm = "test"
//        val limit = 25
//        val expectedResult = ApiResult.Success(MockUtil.mySearch)
//
//        coEvery {
//            mockUsecase.invoke(
//                SearchUsecase.Input(
//                    searchTerm, limit, 0
//                )
//            )
//        } returns flowOf(expectedResult)
//
//        // Set up the compose content
//        composeTestRule.setContent {
//            HomeScreen(viewModel = homeViewModel, onNavigate = { destination ->
//                appDestination = destination
//            })
//        }
//
//        // When - Trigger search and wait for completion
//        homeViewModel.onHandleAction(Actions.onSearch(searchTerm, limit))
//        composeTestRule.waitForIdle()
//
//        // Then - Loading should be hidden after search completes
//        composeTestRule.onNodeWithTag(testTag = loadingTestTag).assertDoesNotExist()
    }
}