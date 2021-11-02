package com.example.playwithcompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesScopesModuleOnAppScope {

    @Singleton
    @ApplicationScopeDefaultDispatcher
    @Provides
    fun providesCoroutineScopeOnDefault(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

    @Singleton
    @ApplicationScopeMainDispatcher
    @Provides
    fun providesCoroutineScopeOnMain(
        @MainDispatcher mainDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + mainDispatcher)


    @Singleton
    @ApplicationScopeIoDispatcher
    @Provides
    fun providesCoroutineScopeOnIo(
        @IoDispatcher IoDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + IoDispatcher)
}


@InstallIn(ActivityRetainedComponent::class)
@Module
object CoroutinesScopesModuleOnViewModelScope {

    @ViewModelScopeDefaultDispatcher
    @Provides
    fun providesCoroutineScopeOnDefault(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

    @ViewModelScopeMainDispatcher
    @Provides
    fun providesCoroutineScopeOnMain(
        @MainDispatcher mainDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + mainDispatcher)

    @ViewModelScopeIoDispatcher
    @Provides
    fun providesCoroutineScopeOnIo(
        @IoDispatcher IoDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + IoDispatcher)
}




