package com.dinh.feature.ui.screen.home

import androidx.lifecycle.viewModelScope
import com.dinh.core.BaseOutput
import com.dinh.core.BaseViewModel
import com.dinh.core.DispatcherProvider
import com.dinh.domain.usecases.SearchUsecase
import com.dinh.domain.util.ApiResult
import com.dinh.feature.UiModel.UiModelSearch
import com.dinh.feature.UiModel.toUiModel
import com.dinh.feature.navigation.AppDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject


sealed class Actions {
    data class onSearch(val term: String, val limit: Int) : Actions()
    object onLoadmore : Actions()
    object clearSearch : Actions()
    data class onClickDetail(val uiModelSearch: UiModelSearch) : Actions()
    object onRefresh : Actions()
}

interface Output : BaseOutput {
    val listSeach: StateFlow<List<UiModelSearch>>
    val isLoading: StateFlow<Boolean>
}


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val searchUsecase: SearchUsecase,
) : BaseViewModel(dispatcherProvider), Output {

    private val default_limmit = 25

    private val _searchQuerry = MutableStateFlow("")
    val searchQuerry: StateFlow<String> get() = _searchQuerry

    private val _currentPage = MutableStateFlow(1)
    val currentPage get() = _currentPage

    private val _limit = MutableStateFlow(default_limmit)
    val limit get() = _limit

    private val _isLoading = MutableStateFlow<Boolean>(false)
    override val isLoading: StateFlow<Boolean>
        get() = _isLoading


    protected val _navigator: MutableSharedFlow<AppDestination> = MutableSharedFlow()
    val navigator: MutableSharedFlow<AppDestination> get() = _navigator

    init {

        viewModelScope.launchWithoutException {
            searchQuerry.debounce(500).distinctUntilChanged().filter { it.isNotEmpty() }
                .collectLatest {
                    _currentPage.update { 0 }
                    search(it, limit.value, currentPage.value)
                }
        }
    }


    override val output: Output get() = this

    private val _listSearch: MutableStateFlow<List<UiModelSearch>> = MutableStateFlow(emptyList())
    override val listSeach: StateFlow<List<UiModelSearch>>
        get() = _listSearch


    fun onHandleAction(action: Actions) {
        when (action) {
            is Actions.onClickDetail -> {
                action.uiModelSearch.collectionId?.let {
                    onItemClick(it)
                }
            }

            Actions.onRefresh -> {
                _currentPage.value = 1
                search(searchQuerry.value, limit.value, currentPage.value)
            }

            is Actions.onSearch -> {
                _searchQuerry.value = action.term
                _listSearch.value = emptyList()
                _limit.value = action.limit
            }

            Actions.clearSearch -> clearSearch()
            Actions.onLoadmore -> search(
                searchQuerry.value, limit.value, currentPage.value, isLoadMore = true
            )
        }
    }

    private fun search(term: String, limit: Int, page: Int, isLoadMore: Boolean = false) {
        viewModelScope.launchWithoutException {
            searchUsecase.invoke(SearchUsecase.Input(term, limit, page))
                .onStart { _isLoading.value = true }.onCompletion { _isLoading.value = false }
                .collect {
                    when (it) {
                        is ApiResult.Error -> {
                            showError(it.message)
                        }

                        is ApiResult.NetworkError -> {
                            showError(it.message)
                        }

                        is ApiResult.Success -> {
                            if (!isLoadMore) {
                                _listSearch.value = it.data.map {
                                    it.toUiModel()
                                }
                            } else {
                                val newData = it.data.map { it.toUiModel() }
                                _listSearch.update { listSeach.value + newData }
                            }


                            _currentPage.update { currentPage.value + 1 }
                        }

                        is ApiResult.UnknowError -> {
                            showError(it.message)
                        }
                    }
                }
        }
    }

    fun onItemClick(id: Long) {
        viewModelScope.launchWithoutException {
            _navigator.emit(AppDestination.DetailScreen.buildDestination(id))
        }
    }

    private fun clearSearch() {
        _searchQuerry.value = ""
        _listSearch.value = emptyList()
        _currentPage.update { 0 }
    }


}