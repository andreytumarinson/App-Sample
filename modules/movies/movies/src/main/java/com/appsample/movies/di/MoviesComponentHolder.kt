package com.appsample.movies.di

import com.appsample.common.di.BaseComponentHolder
import com.appsample.common.di.Component
import com.appsample.movies.domain.repository.MoviesRepository


interface MoviesComponent : Component {

    val moviesRepository: MoviesRepository
}

internal class MoviesComponentImpl(
    dependencies: MoviesDependencies = MoviesDependencies.Impl(),
    private val module: MoviesModule = MoviesModule.Impl(dependencies)
) : MoviesComponent {

    override val moviesRepository get()  = module.moviesRepository
}


object MoviesComponentHolder : BaseComponentHolder<MoviesComponent>() {

    override fun build(): MoviesComponent = MoviesComponentImpl()
}
