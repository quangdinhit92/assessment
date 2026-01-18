package com.dinh.feature.ui.screen.assessment

import com.dinh.core.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class AssessmentViewModelTest {

//    @Mock
//    private lateinit var dispatcherProvider: DispatcherProvider
//
//    private lateinit var viewModel: AssessmentViewModel
//    private val testDispatcher = StandardTestDispatcher()
//
//    @Before
//    fun setup() {
//        MockitoAnnotations.openMocks(this)
//        Dispatchers.setMain(testDispatcher)
//        viewModel = AssessmentViewModel(dispatcherProvider)
//    }
//
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//    }
//
//    @Test
//    fun `loadInitialImages should load 140 images`() = runTest {
//        // Given
//        val expectedImageCount = 140
//
//        // When
//        viewModel.loadInitialImages()
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Then
//        val images = viewModel.images.value
//        assertEquals(expectedImageCount, images?.size)
//    }
//
//    @Test
//    fun `addNewImage should add one image to the list`() = runTest {
//        // Given
//        viewModel.loadInitialImages()
//        testDispatcher.scheduler.advanceUntilIdle()
//        val initialCount = viewModel.images.value?.size ?: 0
//
//        // When
//        viewModel.addNewImage()
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Then
//        val newCount = viewModel.images.value?.size ?: 0
//        assertEquals(initialCount + 1, newCount)
//    }
//
//    @Test
//    fun `reloadAllImages should clear and reload 140 images`() = runTest {
//        // Given
//        viewModel.loadInitialImages()
//        testDispatcher.scheduler.advanceUntilIdle()
//        val initialImages = viewModel.images.value
//
//        // When
//        viewModel.reloadAllImages()
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Then
//        val newImages = viewModel.images.value
//        assertEquals(140, newImages?.size)
//        assertTrue(initialImages != newImages) // Should be different instances
//    }
//
//    @Test
//    fun `onImageLoaded should update image loading state`() = runTest {
//        // Given
//        viewModel.loadInitialImages()
//        testDispatcher.scheduler.advanceUntilIdle()
//        val firstImage = viewModel.images.value?.firstOrNull()
//        assertTrue(firstImage?.isLoading == true)
//
//        // When
//        firstImage?.let { viewModel.onImageLoaded(it.id) }
//
//        // Then
//        val updatedImage = viewModel.images.value?.firstOrNull()
//        assertFalse(updatedImage?.isLoading == true)
//    }
} 