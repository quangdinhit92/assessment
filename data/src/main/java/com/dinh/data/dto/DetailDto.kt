package com.dinh.data.dto

import com.dinh.domain.entities.ItemDetailEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailResponseDto(
    @Json(name = "resultCount") val resultCount: Int,
    @Json(name = "results") val results: List<DetailDto>
)

@JsonClass(generateAdapter = true)
data class DetailDto(
    @Json(name = "wrapperType") val wrapperType: String,
    @Json(name = "artistId") val artistId: Long,
    @Json(name = "collectionId") val collectionId: Long,
    @Json(name = "artistName") val artistName: String,
    @Json(name = "collectionName") val collectionName: String,
    @Json(name = "collectionCensoredName") val collectionCensoredName: String,
    @Json(name = "artistViewUrl") val artistViewUrl: String,
    @Json(name = "collectionViewUrl") val collectionViewUrl: String,
    @Json(name = "artworkUrl60") val artworkUrl60: String,
    @Json(name = "artworkUrl100") val artworkUrl100: String,
    @Json(name = "collectionPrice") val collectionPrice: Double,
    @Json(name = "collectionExplicitness") val collectionExplicitness: String,
    @Json(name = "trackCount") val trackCount: Int,
    @Json(name = "copyright") val copyright: String?,
    @Json(name = "country") val country: String,
    @Json(name = "currency") val currency: String,
    @Json(name = "releaseDate") val releaseDate: String,
    @Json(name = "primaryGenreName") val primaryGenreName: String,
    @Json(name = "previewUrl") val previewUrl: String?,
    @Json(name = "description") val description: String?
)

internal fun DetailDto.toEntity() = ItemDetailEntity(
    artistId = artistId,
    previewUrl = previewUrl ?: "",
    description = description ?: "",
    collectionViewUrl = collectionViewUrl
)