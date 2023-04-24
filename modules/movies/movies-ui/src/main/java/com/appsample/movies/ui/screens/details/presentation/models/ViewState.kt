package com.appsample.movies.ui.screens.details.presentation.models

import com.appsample.movies.domain.models.MovieItemDetails

sealed class ViewState {
    object Loading : ViewState()
    class Data(val data: MovieItemDetails) : ViewState()
    object Error : ViewState()
}
