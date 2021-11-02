package com.example.playwithcompose.data.useCases

import com.example.playwithcompose.data.IStarsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetPopularStarsUseCase @Inject constructor(
    private val repository: IStarsRepository
) {
    operator fun invoke(apiKey: String) =
        repository.getPopularStars(apiKey = apiKey)
}