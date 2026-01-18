package com.dinh.feature.ui.screen.home

import android.util.Log
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
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


sealed class HomeIntents {
    data class SearchQuerry(val term: String, val limit: Int) : HomeIntents()
    object LoadMore : HomeIntents()
    object Refresh : HomeIntents()
    data class OpenDetail(val id: Long) : HomeIntents()
    object ClearSearch : HomeIntents()


}


sealed class HomeEvent {
    data class NavigateTo(val destination: AppDestination) : HomeEvent()
    data class Error(val message: String) : HomeEvent()
}

interface Output : BaseOutput {
    val listSeach: StateFlow<List<UiModelSearch>>
    val isLoading: StateFlow<Boolean>
    val isLoadMore: StateFlow<Boolean>
}


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val searchUsecase: SearchUsecase,
) : BaseViewModel(dispatcherProvider), Output {

    companion object {
        private const val DEBOUNCE_MS = 500L
        private const val PAGE_SZIE = 25
    }

    private val _items = MutableStateFlow<List<UiModelSearch>>(emptyList())
    val items: StateFlow<List<UiModelSearch>> = _items.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    override val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _event = Channel<HomeEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()


    val intent = MutableSharedFlow<HomeIntents>(replay = 0, extraBufferCapacity = 64)

    private var currentPage = 0

    private var currentQuerry: String = ""

    private val default_limmit = 25

    private val _searchQuerry = MutableStateFlow("")
    val searchQuerry: StateFlow<String> get() = _searchQuerry

//    private val _currentPage = MutableStateFlow(1)
//    val currentPage get() = _currentPage

    private val _limit = MutableStateFlow(default_limmit)
    val limit get() = _limit


    private val _isLoadMore = MutableStateFlow<Boolean>(false)
    override val isLoadMore: StateFlow<Boolean>
        get() = _isLoadMore


//    protected val _navigator: MutableSharedFlow<AppDestination> = MutableSharedFlow()
//    val navigator: MutableSharedFlow<AppDestination> get() = _navigator

    init {
        onHandleIntent()
    }


    override val output: Output get() = this

    private val _listSearch: MutableStateFlow<List<UiModelSearch>> = MutableStateFlow(emptyList())
    override val listSeach: StateFlow<List<UiModelSearch>>
        get() = _listSearch



    fun onHandleIntent() {
        intent.filter { it is HomeIntents.SearchQuerry }
            .map { it as HomeIntents.SearchQuerry }
            .onEach {
                _searchQuerry.value = it.term
            }.launchIn(viewModelScope)


        viewModelScope.launchWithoutException {
            intent.filter { it is HomeIntents.SearchQuerry }
                .map { it as HomeIntents.SearchQuerry }
                .onEach {
                    _searchQuerry.value = it.term
                }
                .debounce(DEBOUNCE_MS).distinctUntilChanged()
                .flatMapLatest { intent -> flowOf(intent) }

                .collect {
                    performSeach(it.term)
                }
        }

        viewModelScope.launchWithoutException {


            intent.filter { it !is HomeIntents.SearchQuerry }

                .collect { intent ->
                    when (intent) {
                        HomeIntents.LoadMore -> performLoadMore()
                        is HomeIntents.OpenDetail -> _event.send(
                            HomeEvent.NavigateTo(
                                AppDestination.DetailScreen.buildDestination(
                                    intent.id
                                )
                            )
                        )

                        HomeIntents.Refresh -> {
                            currentPage = 0
                            _items.value = emptyList()
                            performSeach(searchQuerry.value)
                        }

                        HomeIntents.ClearSearch -> {
                            _searchQuerry.value = ""
                            _listSearch.value = emptyList()
                        }

                        else -> {}
                    }

                }
        }
    }

    private suspend fun performSeach(querry: String) {
        currentPage = 0
        currentQuerry = querry
        searchUsecase.invoke(SearchUsecase.Input(querry, PAGE_SZIE, 0))
            .onStart { _isLoading.value = true }
            .catch { e -> _event.send(HomeEvent.Error(e.message ?: "unknown")) }
            .onCompletion { _isLoading.value = false }
            .collect {
                when (it) {
                    is ApiResult.Error -> {
                        showError(it.message)
                    }

                    is ApiResult.NetworkError -> {
                        showError(it.message)
                    }

                    is ApiResult.Success -> {

                        _listSearch.value = it.data.map {
                            it.toUiModel()
                        }

                        currentPage = 1
                    }

                    is ApiResult.UnknowError -> {
                        showError(it.message)
                    }


                    else -> {}
                }

            }
    }

    fun handleIntent(incomming: HomeIntents) {
        viewModelScope.launchWithoutException {
            if (!intent.tryEmit(incomming))
                Log.d("TRY_EMIT", "false------")
        }

    }

    private suspend fun performLoadMore() {
        if (_isLoading.value) return

        searchUsecase.invoke(SearchUsecase.Input(currentQuerry, PAGE_SZIE, currentPage))
            .onStart { _isLoading.value = true }
            .catch { }
            .onCompletion { _isLoading.value = false }
            .collect {
                when (it) {
                    is ApiResult.Error -> {
                        showError(it.message)
                    }

                    is ApiResult.NetworkError -> {
                        showError(it.message)
                    }

                    is ApiResult.Success -> {
                        val newData = it.data.map { it.toUiModel() }
                        _listSearch.update { old -> old + newData }

                        currentPage += 1
                    }

                    is ApiResult.UnknowError -> {
                        showError(it.message)
                    }


                    else -> {}
                }
            }


    }

}