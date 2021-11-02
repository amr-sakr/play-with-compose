package com.example.playwithcompose.data

import com.example.playwithcompose.network.StarApi
import javax.inject.Inject

interface IRemoteDataSource {
    val starApi: StarApi
}

class RemoteDataSource @Inject constructor(
    override val starApi: StarApi
) : IRemoteDataSource