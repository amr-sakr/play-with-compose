package com.example.playwithcompose.data.useCases

import com.example.playwithcompose.data.IStarsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetStarUseCase @Inject constructor(
    private val repository: IStarsRepository
) {
    suspend operator fun invoke(starId: Int, apiKey: String) =
        repository.getStarDetails(starId, apiKey)
}