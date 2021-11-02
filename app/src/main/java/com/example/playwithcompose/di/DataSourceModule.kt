package com.example.playwithcompose.di

import com.example.playwithcompose.data.IRemoteDataSource
import com.example.playwithcompose.data.RemoteDataSource
import com.example.playwithcompose.network.StarApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Provides
    @Singleton
    fun bindIRemoteDataSource(starApi: StarApi): IRemoteDataSource {
        return RemoteDataSource(starApi)
    }
}