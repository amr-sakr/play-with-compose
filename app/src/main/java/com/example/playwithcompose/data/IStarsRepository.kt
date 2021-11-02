package com.example.playwithcompose.data

import androidx.paging.PagingData
import com.example.playwithcompose.entities.StarView
import com.example.playwithcompose.entities.StarDetails
import com.example.playwithcompose.util.Result
import kotlinx.coroutines.flow.Flow

interface IStarsRepository {
    fun getPopularStars(apiKey: String): Flow<PagingData<StarView>>
    suspend fun getStarDetails(starId: Int, apiKey: String): Flow<Result<StarDetails>>
}