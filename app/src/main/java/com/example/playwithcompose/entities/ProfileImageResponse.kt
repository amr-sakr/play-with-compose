package com.example.playwithcompose.entities

import com.google.gson.annotations.SerializedName

data class ProfileImageResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("profiles")
    val profiles: List<Profile>? = null
)