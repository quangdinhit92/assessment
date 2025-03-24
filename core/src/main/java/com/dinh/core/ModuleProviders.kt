package com.dinh.core

import com.dinh.core.qualifier.QualifiterCustomInterceptor
import com.dinh.core.qualifier.QualifiterNetworkInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ModuleProviders {

    @Provides
    fun provideBaseUrl() = "https://itunes.apple.com"


    @Provides
    @QualifiterCustomInterceptor
    fun provideCustomInterceptor(): Interceptor {
        return CustomInterceptor()
    }


    @Provides
    @QualifiterNetworkInterceptor
    fun provideNetworkInterceptor(): Interceptor {
        return NetworkInterceptor()
    }

    @Provides
    fun provideClient(
         customInterceptor: CustomInterceptor,
         networkInterceptor: NetworkInterceptor
    ): OkHttpClient {
        return ClientProvider(customInterceptor, networkInterceptor)
    }


    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    fun provideConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideRetrofit(
        baseUrl: String,
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return RetrofitProvider.getRetrofit(baseUrl, client, converterFactory)
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {
    @Binds
    abstract fun bindDispatcherProvider(dispatcherProvider: DispatcherProviderImpl): DispatcherProvider

}