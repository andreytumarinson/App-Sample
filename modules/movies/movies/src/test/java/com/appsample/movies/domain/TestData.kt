package com.appsample.movies.domain

import com.appsample.movies.domain.models.MovieItemDetails
import com.appsample.movies.data.api.models.CloudMovieItemDetails
import com.appsample.movies.data.db.models.DbMovieItemDetails

internal val testMovie = MovieItemDetails(
    1,
    "Title",
    "url1",
    "url2",
    "Description",
    100000,
    4.5f,
    "2022-22-33",
)

internal val testMovieDb = DbMovieItemDetails(
    1,
    "Title",
    "url1",
    "url2",
    "Description",
    100000,
    4.5f,
    "2022-22-33",
)

internal val testMovieServer = CloudMovieItemDetails(
    1,
    "Title",
    "url1",
    "url2",
    "Description",
    100000,
    4.5f,
    "2022-22-33",
)
