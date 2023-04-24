package com.appsample.movies.ui.screens.list.presentation.models

import com.appsample.movies.domain.models.MovieItem

sealed class ViewState {
    object Loading : ViewState()
    class Data(val data: List<MovieItem>) : ViewState()
    object Error : ViewState()
}
