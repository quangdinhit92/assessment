package com.dinh.core

import com.dinh.core.qualifier.QualifiterCustomInterceptor
import com.dinh.core.qualifier.QualifiterNetworkInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Inject

class ClientProvider @Inject constructor(
   @QualifiterCustomInterceptor customInterceptor: Interceptor,
   @QualifiterNetworkInterceptor networkInterceptor: Interceptor
) :
    OkHttpClient() {
    init {
        Builder().apply {
//            addInterceptor(customInterceptor)
//            addNetworkInterceptor(networkInterceptor)
        }.build()
    }
}