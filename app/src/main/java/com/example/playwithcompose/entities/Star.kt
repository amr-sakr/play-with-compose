package com.example.playwithcompose.entities

import com.google.gson.annotations.SerializedName

data class Star(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("gender")
    val gender: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("known_for")
    val knownFor: List<KnownFor>? = null,
    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null
)

data class StarView(
    @SerializedName("gender")
    var gender: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("known_for")
    val knownFor: List<KnownFor>? = null,
    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null
)

fun Star.toStarView(): StarView {

    return StarView(
        gender = if (gender == 1) {
            "Female"
        } else {
            "Male"
        },
        id = id,
        knownFor = knownFor,
        knownForDepartment = knownForDepartment,
        name = name,
        popularity = popularity,
        profilePath = profilePath
    )

}