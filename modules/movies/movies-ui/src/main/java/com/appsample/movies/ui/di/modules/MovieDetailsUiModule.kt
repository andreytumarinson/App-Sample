package com.appsample.movies.ui.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsample.movies.domain.repository.MoviesRepository
import com.appsample.movies.ui.di.MoviesUiDependencies
import com.appsample.movies.ui.screens.details.presentation.MovieDetailsViewModel

internal interface MovieDetailsUiModule {

    val viewModelFactory: ViewModelProvider.Factory

    class Impl(dependencies: MoviesUiDependencies) : MovieDetailsUiModule, MoviesUiDependencies by dependencies {

        override val viewModelFactory get() = MovieDetailsViewModelFactory(moviesRepository)
    }
}

class MovieDetailsViewModelFactory(private val moviesRepository: MoviesRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(moviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
