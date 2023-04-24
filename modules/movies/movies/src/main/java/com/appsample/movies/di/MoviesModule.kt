package com.appsample.movies.di

import androidx.room.Room
import com.appsample.movies.data.api.MoviesServerApi
import com.appsample.movies.data.db.MoviesDatabase
import com.appsample.movies.domain.repository.MoviesRepository
import com.appsample.movies.domain.repository.MoviesRepositoryImpl
import com.appsample.movies.domain.models.converters.api.MovieItemConverter
import com.appsample.movies.domain.models.converters.api.MovieItemDetailsConverter

internal interface MoviesModule {

    val moviesRepository: MoviesRepository

    class Impl(dependencies: MoviesDependencies) : MoviesModule, MoviesDependencies by dependencies {

        private val serverApi: MoviesServerApi get() = retrofit.create(MoviesServerApi::class.java)

        private val dataBase get() = Room.databaseBuilder(
            context,
            MoviesDatabase::class.java, "movies-database"
        ).build()

        private val dbApi get() = dataBase.moviesDbApi()

        private val movieItemConverter get() = MovieItemConverter(imageUrlBuilder)

        private val movieItemDetailsConverter get() = MovieItemDetailsConverter(imageUrlBuilder)

        override val moviesRepository get() = MoviesRepositoryImpl(
            serverApi,
            dbApi,
            movieItemConverter,
            movieItemDetailsConverter,
        )
    }
}
