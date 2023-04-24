package com.appsample.movies.domain.models

data class MovieItem(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val rating: Float,
    val releaseDate: String,
)
