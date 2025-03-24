package com.dinh.domain.usecases

import com.dinh.domain.repository.IGetDetailRepository
import javax.inject.Inject

class GetDetailUsecase @Inject constructor(private val repository: IGetDetailRepository) {
    operator fun invoke() {
        // Do something
    }
}