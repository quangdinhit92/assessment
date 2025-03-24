package com.dinh.data.datasoure

import com.dinh.data.dto.DetailResponseDto
import com.dinh.data.dto.SearchResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("/search")
    suspend fun search(
        @Query("term") term: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<SearchResponseDto>

    @GET("/lookup")
    suspend fun lookup(
        @Query("id") id: Long
    ): Response<DetailResponseDto>


}