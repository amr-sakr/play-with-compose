package com.example.playwithcompose.di

import com.orhanobut.logger.AndroidLogAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object LoggingModule {

    @Provides
    @Singleton
    fun provideLoggingAdapter(): AndroidLogAdapter {
        return AndroidLogAdapter()
    }
}