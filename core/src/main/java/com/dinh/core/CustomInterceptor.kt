package com.dinh.core

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CustomInterceptor @Inject constructor (): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }
}