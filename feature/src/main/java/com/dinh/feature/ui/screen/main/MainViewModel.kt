package com.dinh.feature.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MainUiState(
    val selectedNavId: Int = 1,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun selectNavigationItem(navId: Int) {
        _uiState.value = _uiState.value.copy(
            selectedNavId = navId,
            errorMessage = null
        )

        when (navId) {
            1 -> loadHomeData()
            2 -> loadPlotsData()
            3 -> loadTasksData()
            4 -> loadEarningsData()
            5 -> loadProfileData()
        }
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            setLoading(true)
            try {
                kotlinx.coroutines.delay(500)
            } catch (e: Exception) {
                setError("Failed to load home data: ${e.message}")
            } finally {
                setLoading(false)
            }
        }
    }

    private fun loadPlotsData() {
        viewModelScope.launch {
            setLoading(true)
            try {
                kotlinx.coroutines.delay(500)
            } catch (e: Exception) {
                setError("Failed to load plots data: ${e.message}")
            } finally {
                setLoading(false)
            }
        }
    }

    private fun loadTasksData() {
        viewModelScope.launch {
            setLoading(true)
            try {
                kotlinx.coroutines.delay(500)
            } catch (e: Exception) {
                setError("Failed to load tasks data: ${e.message}")
            } finally {
                setLoading(false)
            }
        }
    }

    private fun loadEarningsData() {
        viewModelScope.launch {
            setLoading(true)
            try {
                kotlinx.coroutines.delay(500)
            } catch (e: Exception) {
                setError("Failed to load earnings data: ${e.message}")
            } finally {
                setLoading(false)
            }
        }
    }

    private fun loadProfileData() {
        viewModelScope.launch {
            setLoading(true)
            try {
                kotlinx.coroutines.delay(500)
            } catch (e: Exception) {
                setError("Failed to load profile data: ${e.message}")
            } finally {
                setLoading(false)
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        _uiState.value = _uiState.value.copy(isLoading = isLoading)
    }

    private fun setError(message: String) {
        _uiState.value = _uiState.value.copy(errorMessage = message)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}