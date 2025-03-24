package com.dinh.feature.test

import com.dinh.domain.entities.ItemSearchEntity

object MockUtil {
    val mySearch = listOf(
        ItemSearchEntity(
            artistId = 1000,
            collectionId = -2000,
            artistName = "artistName00",
            collectionName = "collectionName)1", artworkUrl600 = "http://avatar",
        ), ItemSearchEntity(
            artistId = 1001,
            collectionId = 2001,
            artistName = "artistName01",
            collectionName = "collectionName)1", artworkUrl600 = "http://avatar",
        ), ItemSearchEntity(
            artistId = 1002,
            collectionId = 2002,
            artistName = "artistName02",
            collectionName = "collectionName)1", artworkUrl600 = "http://avatar",
        ), ItemSearchEntity(
            artistId = 1003,
            collectionId = 20003,
            artistName = "artistName03s",
            collectionName = "collectionName)1", artworkUrl600 = "http://avatar",
        )
    )
}