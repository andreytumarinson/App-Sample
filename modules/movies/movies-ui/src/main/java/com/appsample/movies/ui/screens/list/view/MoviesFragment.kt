package com.appsample.movies.ui.screens.list.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.allViews
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.appsample.movies.ui.R
import com.appsample.movies.ui.databinding.FragmentMoviesBinding
import com.appsample.movies.ui.di.MoviesUiComponentHolder
import com.appsample.movies.ui.screens.details.view.MovieDetailsFragment
import com.appsample.movies.ui.screens.list.presentation.MoviesViewModel
import com.appsample.movies.ui.screens.list.presentation.models.ViewState
import com.appsample.movies.ui.screens.list.view.adapter.MoviesListAdapter
import kotlinx.coroutines.launch


class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MoviesViewModel by viewModels { viewModelFactory }

    private val moviesListAdapter = MoviesListAdapter { openDetails(it) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MoviesUiComponentHolder.get().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareViews()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { handleState(it) }
            }
        }
    }

    private fun prepareViews() {
        binding.run {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerView.adapter = moviesListAdapter

            errorView.retryBtn.setOnClickListener { viewModel.loadData() }
        }
    }

    private fun handleState(state: ViewState) {
        binding.run {
            when (state) {
                ViewState.Loading -> {
                    dataView.isVisible = false
                    progressView.root.isVisible = true
                    errorView.root.isVisible = false
                }
                is ViewState.Data -> {
                    moviesListAdapter.submitList(state.data)
                    dataView.isVisible = true
                    progressView.root.isVisible = false
                    errorView.root.isVisible = false
                }
                is ViewState.Error -> {
                    dataView.isVisible = false
                    progressView.root.isVisible = false
                    errorView.root.isVisible = true
                }
            }
        }
    }

    private fun openDetails(movieId: Int) {
        parentFragmentManager.commit {
            replace(R.id.container, MovieDetailsFragment.newInstance(movieId))
            addToBackStack(null)
        }
    }
}
