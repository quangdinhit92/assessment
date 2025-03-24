package com.dinh.data.repository

import com.dinh.core.callApi
import com.dinh.data.datasoure.ApiServices
import com.dinh.data.dto.toEntity
import com.dinh.domain.entities.ItemDetailEntity
import com.dinh.domain.repository.IGetDetailRepository
import com.dinh.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetDetailRepositoryImpl @Inject constructor(private val api: ApiServices) :
    IGetDetailRepository {
    override fun invoke(id: Long): Flow<ApiResult<List<ItemDetailEntity>>> = flow {
        callApi {
            api.lookup(id)
        }.map {
            when (it) {
                is ApiResult.Error -> ApiResult.Error(it.code, it.message)
                is ApiResult.NetworkError -> ApiResult.NetworkError(it.message)
                is ApiResult.Success -> ApiResult.Success(it.data.results.map { it.toEntity() })
                is ApiResult.UnknowError ->  ApiResult.UnknowError(it.message)
            }

        }.onStart { }.onCompletion { }.collect {
            emit(it)


        }
    }
}