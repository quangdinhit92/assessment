package com.dinh.feature.UiModel

import com.dinh.domain.entities.ItemDetailEntity

data class UiModelDetail(
    val artistId: Long,
    val previewUrl: String,
    val description: String,
    val collectionViewUrl: String
)

fun ItemDetailEntity.toUIModel() = UiModelDetail(
    artistId = artistId,
    previewUrl = previewUrl,
    description = description,
    collectionViewUrl = collectionViewUrl
)