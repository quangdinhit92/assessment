package com.dinh.feature.di

import com.dinh.data.datasoure.ApiServices
import com.dinh.data.repository.GetDetailRepositoryImpl
import com.dinh.data.repository.SearchRepositoryImpl
import com.dinh.domain.repository.IGetDetailRepository
import com.dinh.domain.repository.ISearchRepository
import com.dinh.feature.fortestingpurpose.HeavyComputationTemplate
import com.dinh.feature.fortestingpurpose.HeavyComputationTemplateImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ModuleProvider {

    @Provides
    fun provideApi(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)

    @Provides
    fun provideSearchRepository(api: ApiServices): ISearchRepository {
        return SearchRepositoryImpl(api)
    }

    @Provides
    fun provideDetailRepository(api: ApiServices): IGetDetailRepository {
        return GetDetailRepositoryImpl(api)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindObject() {
    @Binds
    abstract fun bindExampleRepo(heavyComputationTemplate: HeavyComputationTemplateImpl): HeavyComputationTemplate

}