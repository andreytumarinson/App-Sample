package com.appsample.movies.domain.models.converters.db

import com.appsample.movies.data.db.models.DbMovieItemDetails
import com.appsample.movies.domain.models.MovieItem

internal fun MovieItem.toDb(): DbMovieItemDetails {
    return DbMovieItemDetails(
        id = id,
        title = title,
        posterUrl = posterUrl,
        backdropUrl = "",
        overview = "",
        budget = 0,
        rating = rating,
        releaseDate = releaseDate,
    )
}

internal fun DbMovieItemDetails.toApp(): MovieItem {
    return MovieItem(
        id = id,
        title = title,
        posterUrl = posterUrl,
        rating = rating,
        releaseDate = releaseDate,
    )
}
