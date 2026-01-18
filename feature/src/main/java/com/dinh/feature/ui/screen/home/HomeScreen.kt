package com.dinh.feature.ui.screen.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshDefaults
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.dinh.core.UiState
import com.dinh.feature.UiModel.UiModelSearch
import com.dinh.feature.navigation.AppDestination
import com.dinh.feature.ui.component.SearchItem
import com.dinh.feature.ui.preview.HomePreviewParameter
import com.dinh.feature.ui.preview.HomePreviewParameterProvider


const val loadingTestTag ="loadingTag"

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), onNavigate: (AppDestination) -> Unit) {
    val context = LocalContext.current
    LaunchedEffect(viewModel.commonState) {
        viewModel.commonState.collect {
            when (it) {
                is UiState.Error -> {
                    Log.d("ERROR", it.message)
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }

                UiState.Idle -> {

                }

                is UiState.Loading -> {

                }

                is UiState.Message -> {

                }
            }
        }
    }


    LaunchedEffect(viewModel.event) {
        viewModel.event.collect {
            when(it)
            {
                is HomeEvent.NavigateTo -> onNavigate(it.destination)
                is HomeEvent.Error -> TODO()
            }
        }
    }

    var refreshing by remember {
        mutableStateOf(false)
    }


    val loading by viewModel.output.isLoading.collectAsState()


    val items: List<UiModelSearch> by viewModel.output.listSeach.collectAsState()

    val searchString by viewModel.searchQuerry.collectAsState("")

    HomeContent(
        loading,
        searchString,
        onChange = {
                        viewModel.handleIntent(HomeIntents.SearchQuerry(it,25))
                   },
        onClearSearch = {
            viewModel.handleIntent(HomeIntents.ClearSearch)
        },
        onItemClick = {
            viewModel.handleIntent(HomeIntents.OpenDetail(it.collectionId!!))
        },
        onloadMore = {
            viewModel.handleIntent(HomeIntents.LoadMore)
        },
        onRefresh = {
            viewModel.handleIntent(HomeIntents.Refresh)
        },
        isRefreshing = refreshing,
        items
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeContent(
    isLoading: Boolean,
    search: String,
    onChange: (String) -> Unit,
    onClearSearch: () -> Unit,
    onItemClick: (UiModelSearch) -> Unit,
    onloadMore: () -> Unit,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    searchResult: List<UiModelSearch>
) {
    val LOAD_MORE_THRESHOLD = 5
    val lastIndexOfResult = searchResult.lastIndex
    val listItemsState = rememberLazyListState()
    val refreshingState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = onRefresh,
        refreshingOffset = PullRefreshDefaults.RefreshingOffset,
        refreshThreshold = PullRefreshDefaults.RefreshThreshold
    )

    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .pullRefresh(state = refreshingState), contentAlignment = Alignment.Center
        ) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {

                TextField(modifier = Modifier.fillMaxWidth(),
                    value = search,
                    onValueChange = { onChange.invoke(it) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    },
                    trailingIcon = {
                        IconButton(onClick = { onClearSearch.invoke() }) {
                            if (search.isNotEmpty()) Icon(
                                imageVector = Icons.Default.Clear, contentDescription = null
                            )
                        }

                    },
                    placeholder = { Text(text = "Search") })
                if (searchResult.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "No result found")
                    }

                } else {
                    LazyColumn(state = listItemsState) {
                        item {
                            Text("List of Item: ${searchResult.size}")
                        }
                        itemsIndexed(searchResult) { index, item ->
                            if (LOAD_MORE_THRESHOLD + index > lastIndexOfResult) {
                                SideEffect {
                                    onloadMore.invoke()
                                }
                            }

                            SearchItem(item, onItemClick)
                        }
                    }
                }
            }

            if (isLoading) Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray)
                    .testTag(loadingTestTag)
                ,
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
            {
                PullRefreshIndicator(
                    state = refreshingState,
                    refreshing = isRefreshing
                )
            }


        }
    }

}

@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=240")
@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable()
fun previewHomeScreen(@PreviewParameter(HomePreviewParameterProvider::class) previewParam: HomePreviewParameter) {
    HomeContent(
        previewParam.isLoading,
        previewParam.seachString,
        {},
        {},
        {},
        {},
        {},
        previewParam.isLoading,
        previewParam.listSearchItem
    )
}