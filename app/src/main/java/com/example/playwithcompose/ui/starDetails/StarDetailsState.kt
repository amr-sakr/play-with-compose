package com.example.playwithcompose.ui.starDetails

import com.example.playwithcompose.entities.StarDetails

data class StarDetailsState(
    val isLoading: Boolean = false,
    val star: StarDetails? = null,
    val error: String = ""
)