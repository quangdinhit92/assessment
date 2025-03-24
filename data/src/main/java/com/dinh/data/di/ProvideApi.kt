package com.dinh.data.di

import com.dinh.data.datasoure.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
//
//@Module
//@InstallIn(SingletonComponent::class)
//class ProvideApi {
//    @Provides
//    fun provideApi(retrofit: Retrofit):ApiServices = retrofit.create(ApiServices::class.java)
//}