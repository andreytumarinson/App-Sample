package com.appsample.movies.data.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class CloudMoviesResponse(
    @SerialName("page") val page: Int,
    @SerialName("results") val result: List<CloudMovieItem>,
)

@Serializable
internal data class CloudMovieItem(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("vote_average") val rating: Float,
    @SerialName("release_date") val releaseDate: String,
)
