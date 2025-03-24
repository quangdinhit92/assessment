package com.dinh.domain.repository

import com.dinh.domain.entities.ItemDetailEntity
import com.dinh.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface IGetDetailRepository {
    operator fun invoke(id: Long): Flow<ApiResult<List<ItemDetailEntity>>>
}