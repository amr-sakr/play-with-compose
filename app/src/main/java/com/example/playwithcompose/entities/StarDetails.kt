package com.example.playwithcompose.entities


import com.google.gson.annotations.SerializedName

data class StarDetails(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("biography")
    val biography: String? = null,
    @SerializedName("birthday")
    val birthday: String? = null,
    @SerializedName("deathday")
    val deathday: String? = null,
    @SerializedName("gender")
    val gender: Int? = null,
    @SerializedName("homepage")
    val homepage: Any? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("imdb_id")
    val imdbId: String? = null,
    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("place_of_birth")
    val placeOfBirth: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null
)