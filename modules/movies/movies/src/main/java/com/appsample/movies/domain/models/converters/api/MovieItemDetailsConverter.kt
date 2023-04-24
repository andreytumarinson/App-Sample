package com.appsample.movies.domain.models.converters.api

import com.appsample.common.api.ImageUrlBuilder
import com.appsample.movies.data.api.models.CloudMovieItemDetails
import com.appsample.movies.domain.models.MovieItemDetails

internal class MovieItemDetailsConverter(
    private val imageUrlBuilder: ImageUrlBuilder
) {
    fun toApp(response: CloudMovieItemDetails) : MovieItemDetails {
        return response.run {
            MovieItemDetails(
                id = id,
                title = title,
                backdropUrl = imageUrlBuilder.buildUrl(backdropPath),
                posterUrl = imageUrlBuilder.buildUrl(posterPath),
                overview = overview,
                budget = budget,
                rating = rating,
                releaseDate = releaseDate,
            )
        }
    }
}

/*
internal fun CloudMovieItemDetails.toApp(): MovieItemDetails {
    return MovieItemDetails(
        id = id,
        title = title,
        posterPath = posterPath,
        overview = overview,
        budget = budget,
        rating = rating,
        //productionCompanies = productionCompanies.map { it.toApp() },
    )
}

internal fun CloudProductionCompany.toApp(): ProductionCompany {
    return ProductionCompany(
        title = title,
        posterPath = posterPath,
    )
}
*/
