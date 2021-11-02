package com.example.playwithcompose.network

import com.example.playwithcompose.entities.ProfileImageResponse
import com.example.playwithcompose.entities.StarsListResponse
import com.example.playwithcompose.entities.StarDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarApi {

    @GET("person/popular")
    suspend fun getPopularPeopleList(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): Response<StarsListResponse>


    @GET("person/{id}/images")
    suspend fun getPersonProfileImages(
        @Path("id") personId: Int,
        @Query("api_key") apiKey: String
    ): Response<ProfileImageResponse>


    @GET("person/{id}")
    suspend fun getStarDetails(
        @Path("id") starId: Int,
        @Query("api_key") apiKey: String
    ): Response<StarDetails>
}