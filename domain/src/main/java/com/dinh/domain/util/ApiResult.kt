package com.dinh.domain.util

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val code: Int, val message: String) : ApiResult<Nothing>()
    data class NetworkError(val message: String) : ApiResult<Nothing>()
    data class UnknowError(val message: String) : ApiResult<Nothing>()

}