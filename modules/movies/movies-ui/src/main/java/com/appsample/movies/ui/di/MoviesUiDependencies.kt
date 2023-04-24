package com.appsample.movies.ui.di

import com.appsample.movies.di.MoviesComponentHolder
import com.appsample.movies.domain.repository.MoviesRepository

internal interface MoviesUiDependencies {

    val moviesRepository: MoviesRepository

    class Impl : MoviesUiDependencies {
        override val moviesRepository get() = MoviesComponentHolder.get().moviesRepository
    }
}
