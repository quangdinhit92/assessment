package com.dinh.core

import com.dinh.domain.util.ApiResult
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.Response

inline fun <T> callApi(crossinline block: suspend FlowCollector<ApiResult<T>>.() -> Response<T>) =
    flow<ApiResult<T>> {
        //throw RuntimeException("Something went wrong!")
        try {

            val ret = block()
            val body = ret.body()
            if (ret.isSuccessful && null != body) {
                emit(ApiResult.Success(body))
            } else {
                emit(ApiResult.Error(ret.code(), ret.message()))
            }
        } catch (e: Exception) {
            emit(ApiResult.UnknowError(e.toString()))
        }

    }

