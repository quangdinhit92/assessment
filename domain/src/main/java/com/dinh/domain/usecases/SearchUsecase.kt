package com.dinh.domain.usecases

import com.dinh.domain.entities.ItemSearchEntity
import com.dinh.domain.repository.ISearchRepository
import com.dinh.domain.util.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUsecase @Inject constructor(private val repository: ISearchRepository) {
    data class Input(
        val term: String,
        val limit: Int,
        val page: Int
    )

    operator fun invoke(input: Input): Flow<ApiResult<List<ItemSearchEntity>>> {
        return repository.invoke(input.term, input.limit, input.page)
    }
}