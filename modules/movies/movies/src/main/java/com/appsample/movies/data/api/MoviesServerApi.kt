package com.appsample.movies.data.api

import com.appsample.movies.data.api.models.CloudMovieItemDetails
import com.appsample.movies.data.api.models.CloudMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MoviesServerApi {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): CloudMoviesResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): CloudMovieItemDetails
}
