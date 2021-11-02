package com.example.playwithcompose.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.playwithcompose.di.IoDispatcher
import com.example.playwithcompose.entities.StarDetails
import com.example.playwithcompose.entities.StarView
import com.example.playwithcompose.util.NetworkUtils
import com.example.playwithcompose.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

const val NETWORK_PAGE_SIZE = 20

@Singleton
class StarsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher
    private val coroutineDispatcher: CoroutineDispatcher
) : IStarsRepository {

    @Inject
    lateinit var networkUtils: NetworkUtils

    override fun getPopularStars(apiKey: String): Flow<PagingData<StarView>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PopularStarsPagingSource(
                    remoteDataSource = remoteDataSource,
                    apiKey = apiKey
                )
            }
        ).flow
    }

    override suspend fun getStarDetails(starId: Int, apiKey: String): Flow<Result<StarDetails>> {
        val result = remoteDataSource.starApi.getStarDetails(starId, apiKey)
        val flow = flow {
            try {
                if (!networkUtils.isConnectedToNetwork()) {
                    throw IOException("Please check your internet connection")
                }
                emit(Result.Success(result.body()!!))
            } catch (e: HttpException) {
                emit(Result.Error(e.message ?: "Something went wrong, please try again later"))
            } catch (e: IOException) {
                emit(Result.Error(e.message ?: "Something went wrong, please try again later"))
            }
        }.flowOn(coroutineDispatcher)
        return flow
    }

}