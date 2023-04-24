package com.appsample.movies.domain.models

data class MovieItemDetails(
    val id: Int,
    val title: String,
    val backdropUrl: String,
    val posterUrl: String,
    val overview: String,
    val budget: Int,
    val rating: Float,
    val releaseDate: String,
)
