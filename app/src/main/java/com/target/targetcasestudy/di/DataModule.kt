package com.target.targetcasestudy.di

import com.target.targetcasestudy.api.TargetApi
import com.target.targetcasestudy.data.deal.local.DealLocalDataSource
import com.target.targetcasestudy.data.deal.local.DealLocalDataSourceImpl
import com.target.targetcasestudy.data.deal.remote.DealRemoteDataSource
import com.target.targetcasestudy.data.deal.remote.DealRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDealLocalDataSource(): DealLocalDataSource {
        return DealLocalDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideDealRemoteDataSource(apiService: TargetApi): DealRemoteDataSource {
        return DealRemoteDataSourceImpl(apiService)
    }
}