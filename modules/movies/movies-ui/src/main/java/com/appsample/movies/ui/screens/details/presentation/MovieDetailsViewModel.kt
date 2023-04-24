package com.appsample.movies.ui.screens.details.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsample.movies.domain.repository.MoviesRepository
import com.appsample.movies.ui.screens.details.presentation.models.ViewState
import com.appsample.movies.ui.utils.runCatchingNonCancellation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class MovieDetailsViewModel(private val moviesRepo: MoviesRepository) : ViewModel() {

    private val _state = MutableStateFlow<ViewState>(ViewState.Loading)
    val state get() = _state.asStateFlow()

    private var isFirstStart: Boolean = true
    private var movieId by Delegates.notNull<Int>()

    fun init(movieId: Int) {
        if(isFirstStart) {
            this.movieId = movieId
            loadData()
            isFirstStart = false
        }
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatchingNonCancellation {
                _state.emit(ViewState.Loading)
                val movie = moviesRepo.getMovieDetails(movieId)
                _state.emit(ViewState.Data(movie))
            }.onFailure {
                Log.e(MovieDetailsViewModel::class.simpleName, "Failed to load ad info", it)
                _state.emit(ViewState.Error)
            }
        }
    }
}
