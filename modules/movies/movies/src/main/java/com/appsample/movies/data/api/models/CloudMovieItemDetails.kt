package com.appsample.movies.data.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class CloudMovieItemDetails(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("backdrop_path") val backdropPath: String,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("overview") val overview: String,
    @SerialName("budget") val budget: Int,
    @SerialName("vote_average") val rating: Float,
    @SerialName("release_date") val releaseDate: String,
)
