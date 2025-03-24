package com.dinh.domain.repository

import com.dinh.domain.entities.ItemSearchEntity
import com.dinh.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {
    operator fun invoke(term: String, limit: Int,page: Int ): Flow<ApiResult<List<ItemSearchEntity>>>
}