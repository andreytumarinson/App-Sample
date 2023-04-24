package com.appsample.movies.ui.di

import com.appsample.common.di.BaseComponentHolder
import com.appsample.common.di.Component
import com.appsample.movies.ui.di.modules.MovieDetailsUiModule
import com.appsample.movies.ui.di.modules.MoviesUiModule
import com.appsample.movies.ui.screens.details.view.MovieDetailsFragment
import com.appsample.movies.ui.screens.list.view.MoviesFragment


interface MoviesUiComponent : Component {

    fun inject(screen: MoviesFragment)

    fun inject(screen: MovieDetailsFragment)
}

internal class MoviesUiComponentImpl(
    dependencies: MoviesUiDependencies = MoviesUiDependencies.Impl(),
    private val listModule: MoviesUiModule = MoviesUiModule.Impl(dependencies),
    private val detailsModule: MovieDetailsUiModule = MovieDetailsUiModule.Impl(dependencies)
) : MoviesUiComponent {

    override fun inject(screen: MoviesFragment) {
        screen.viewModelFactory = listModule.viewModelFactory
    }

    override fun inject(screen: MovieDetailsFragment) {
        screen.viewModelFactory = detailsModule.viewModelFactory
    }
}

object MoviesUiComponentHolder : BaseComponentHolder<MoviesUiComponent>() {

    override fun build(): MoviesUiComponent = MoviesUiComponentImpl()
}
