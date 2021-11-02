package com.example.playwithcompose.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.playwithcompose.entities.StarView
import com.example.playwithcompose.entities.toStarView
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class PopularStarsPagingSource(
    private val remoteDataSource: RemoteDataSource,
    private val apiKey: String,
) : PagingSource<Int, StarView>() {
    override fun getRefreshKey(state: PagingState<Int, StarView>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StarView> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = remoteDataSource.starApi.getPopularPeopleList(apiKey, page = position)
            val starsList = response.body()?.results?.map { star ->
                star.toStarView()
            } ?: listOf()
            val nextKey = if (starsList.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = starsList,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}