package com.example.playwithcompose.di

import com.example.playwithcompose.data.IStarsRepository
import com.example.playwithcompose.data.StarsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideHomeRepository(repository: StarsRepository): IStarsRepository = repository

}
