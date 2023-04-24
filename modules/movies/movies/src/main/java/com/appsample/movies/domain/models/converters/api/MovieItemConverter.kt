package com.appsample.movies.domain.models.converters.api

import com.appsample.common.api.ImageUrlBuilder
import com.appsample.movies.data.api.models.CloudMoviesResponse
import com.appsample.movies.domain.models.MovieItem

internal class MovieItemConverter(
    private val imageUrlBuilder: ImageUrlBuilder
) {
    fun toApp(response: CloudMoviesResponse): List<MovieItem> {
        return response.result.map {
            MovieItem(
                id = it.id,
                title = it.title,
                posterUrl = imageUrlBuilder.buildUrl(it.posterPath),
                rating = it.rating,
                releaseDate = it.releaseDate,
            )
        }
    }
}
