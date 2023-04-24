package com.appsample.movies.domain.models.converters.db

import com.appsample.movies.data.db.models.DbMovieItemDetails
import com.appsample.movies.domain.models.MovieItemDetails

internal fun MovieItemDetails.toDb(): DbMovieItemDetails {
    return DbMovieItemDetails(
        id = id,
        title = title,
        posterUrl = posterUrl,
        backdropUrl = backdropUrl,
        overview = overview,
        budget = budget,
        rating = rating,
        releaseDate = releaseDate,
    )
}

internal fun DbMovieItemDetails.toAppDetails(): MovieItemDetails {
    return MovieItemDetails(
        id = id,
        title = title,
        posterUrl = posterUrl,
        backdropUrl = backdropUrl,
        overview = overview,
        budget = budget,
        rating = rating,
        releaseDate = releaseDate,
    )
}
