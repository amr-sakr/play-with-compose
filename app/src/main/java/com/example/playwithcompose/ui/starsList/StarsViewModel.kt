package com.example.playwithcompose.ui.starsList

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.playwithcompose.BuildConfig
import com.example.playwithcompose.data.useCases.GetPopularStarsUseCase
import com.example.playwithcompose.entities.StarView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class StarsViewModel @Inject constructor(
    getPopularStarsUseCase: GetPopularStarsUseCase
) : ViewModel() {
    val popularStars: Flow<PagingData<StarView>> = getPopularStarsUseCase(BuildConfig.API_KEY)
}