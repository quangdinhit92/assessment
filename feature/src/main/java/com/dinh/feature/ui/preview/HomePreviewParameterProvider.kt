package com.dinh.feature.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.dinh.feature.UiModel.UiModelSearch

data class HomePreviewParameter(
    val isRefreshing: Boolean,
    val isLoading: Boolean,
    val seachString: String,
    val listSearchItem: List<UiModelSearch> = listOf(
        UiModelSearch(
            1, 1, "artistName", "collectionName", "artworkUrl600"
        ), UiModelSearch(
            1, 1, "artistName", "collectionName", "artworkUrl600"
        ), UiModelSearch(
            1, 1, "artistName", "collectionName", "artworkUrl600"
        )
    )
)

class HomePreviewParameterProvider : PreviewParameterProvider<HomePreviewParameter> {
    override val values: Sequence<HomePreviewParameter>
        get() = sequenceOf(
            HomePreviewParameter(false, true, "happy new year"),
            HomePreviewParameter(true, false, "happy new year"),
            HomePreviewParameter(true, false, "", emptyList())
        )
}