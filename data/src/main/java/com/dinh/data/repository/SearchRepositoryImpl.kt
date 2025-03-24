package com.dinh.data.repository

import com.dinh.core.callApi
import com.dinh.data.datasoure.ApiServices
import com.dinh.data.dto.toEntity
import com.dinh.domain.entities.ItemSearchEntity
import com.dinh.domain.repository.ISearchRepository
import com.dinh.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val api: ApiServices) : ISearchRepository {
    override fun invoke(
        term: String, limit: Int,page: Int): Flow<ApiResult<List<ItemSearchEntity>>> = flow {
        callApi {
            api.search(term, page, limit)
        }.map {
            when (it) {
                is ApiResult.Error -> it
                is ApiResult.NetworkError -> it
                is ApiResult.Success -> ApiResult.Success(it.data.results.map { it.toEntity() })
                is ApiResult.UnknowError -> it
            }
        }.collect {
            emit(it)
        }
    }

}