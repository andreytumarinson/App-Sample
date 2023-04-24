package com.appsample.movies.domain.repository

import com.appsample.movies.domain.models.MovieItem
import com.appsample.movies.domain.models.MovieItemDetails

interface MoviesRepository {

    suspend fun getPopularMovies(): List<MovieItem>

    suspend fun getMovieDetails(movieId: Int): MovieItemDetails
}
