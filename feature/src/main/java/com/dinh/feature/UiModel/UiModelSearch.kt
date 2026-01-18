package com.dinh.feature.UiModel

import com.dinh.domain.entities.ItemSearchEntity

data class  UiModelSearch(
    val artistId: Long?,
    val collectionId: Long?,
    val artistName: String,
    val collectionName: String?,
    val artworkUrl600: String,
)

fun ItemSearchEntity.toUiModel() = UiModelSearch(
    artistId = artistId,
    collectionId = collectionId,
    artistName = artistName,
    collectionName = collectionName,
    artworkUrl600 = artworkUrl600
)