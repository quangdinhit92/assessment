package com.dinh.domain.entities

data class ItemSearchEntity(
    val artistId: Long?,
    val collectionId: Long?,
    val artistName: String,
    val collectionName: String?,
    val artworkUrl600: String,
)