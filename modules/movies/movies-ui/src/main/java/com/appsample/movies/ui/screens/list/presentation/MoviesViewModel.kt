package com.appsample.movies.ui.screens.list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsample.movies.domain.repository.MoviesRepository
import com.appsample.movies.ui.screens.list.presentation.models.ViewState
import com.appsample.movies.ui.utils.runCatchingNonCancellation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(private val moviesRepo: MoviesRepository) : ViewModel() {

    private val _state = MutableStateFlow<ViewState>(ViewState.Loading)
    val state get() = _state.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatchingNonCancellation {
                _state.emit(ViewState.Loading)
                val movies = moviesRepo.getPopularMovies()
                _state.emit(ViewState.Data(movies))
            }.onFailure {
                Log.e(MoviesViewModel::class.simpleName, "Failed to load ad info", it)
                _state.emit(ViewState.Error)
            }
        }
    }
}
