package com.appsample.movies.domain.repository

import com.appsample.common.domain.getFromSources
import com.appsample.movies.data.api.MoviesServerApi
import com.appsample.movies.data.db.MoviesDbApi
import com.appsample.movies.domain.models.MovieItem
import com.appsample.movies.domain.models.MovieItemDetails
import com.appsample.movies.domain.models.converters.api.MovieItemConverter
import com.appsample.movies.domain.models.converters.api.MovieItemDetailsConverter
import com.appsample.movies.domain.models.converters.db.toApp
import com.appsample.movies.domain.models.converters.db.toAppDetails
import com.appsample.movies.domain.models.converters.db.toDb

internal class MoviesRepositoryImpl constructor(
    private val serverApi: MoviesServerApi,
    private val dbApi: MoviesDbApi,
    private val movieItemConverter: MovieItemConverter,
    private val movieItemDetailsConverter: MovieItemDetailsConverter,
) : MoviesRepository {

    override suspend fun getPopularMovies(): List<MovieItem> {
        return getFromSources(
            fromServerCall = { movieItemConverter.toApp(serverApi.getPopularMovies()) },
            fromDbCall = { getPopularMoviesFromDb() },
            toDbCall = { data -> dbApi.replaceMovies(data.map { it.toDb() }) },
        )
    }

    override suspend fun getMovieDetails(movieId: Int): MovieItemDetails {
        return getFromSources(
            fromServerCall = { movieItemDetailsConverter.toApp(serverApi.getMovieDetails(movieId)) },
            fromDbCall = { getMovieFromDb(movieId) },
            toDbCall = { dbApi.insertMovie(it.toDb()) },
        )
    }

    private fun getPopularMoviesFromDb(): List<MovieItem>? {
        return dbApi.getMovies()
            .map { it.toApp() }
            .takeIf { it.isNotEmpty() }
    }

    private fun getMovieFromDb(movieId: Int): MovieItemDetails? {
        return dbApi.getMovie(movieId)?.toAppDetails()
    }
}

