package com.dinh.core

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

object RetrofitProvider {
    fun getRetrofit(
        baseUrl: String, client: OkHttpClient, converterFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(converterFactory).client(client)
            .build()
}