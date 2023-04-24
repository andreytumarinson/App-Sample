package com.appsample.movies.ui.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsample.movies.domain.repository.MoviesRepository
import com.appsample.movies.ui.di.MoviesUiDependencies
import com.appsample.movies.ui.screens.list.presentation.MoviesViewModel

internal interface MoviesUiModule {

    val viewModelFactory: ViewModelProvider.Factory

    class Impl(dependencies: MoviesUiDependencies) : MoviesUiModule, MoviesUiDependencies by dependencies {

        override val viewModelFactory get() = MoviesViewModelFactory(moviesRepository)
    }
}

class MoviesViewModelFactory(private val moviesRepository: MoviesRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(moviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
