package com.appsample.movies.ui.screens.details.view

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.appsample.movies.domain.models.MovieItemDetails
import com.appsample.movies.ui.R
import com.appsample.movies.ui.databinding.FragmentMovieDetailsBinding
import com.appsample.movies.ui.di.MoviesUiComponentHolder
import com.appsample.movies.ui.screens.details.presentation.MovieDetailsViewModel
import com.appsample.movies.ui.screens.details.presentation.models.ViewState
import com.appsample.movies.ui.utils.FormatUtils
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch


class MovieDetailsFragment : Fragment(), MenuProvider {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MovieDetailsViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MoviesUiComponentHolder.get().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareToolbar()
        prepareViews()

        arguments?.let {
            viewModel.init(it.getInt(MOVIE_ID))
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { handleState(it) }
            }
        }
    }

    private fun prepareViews() {
        binding.run {
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
                    fillData(state.data)
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

    private fun fillData(movie: MovieItemDetails) {
        binding.detailsContainer.run {
            title.text = movie.title
            rating.text = FormatUtils.formatRating(requireContext(), movie.rating)
            date.text = movie.releaseDate
            budget.text = FormatUtils.formatMoney(requireContext(), movie.budget)
            description.text = movie.overview
            setImage(movie.backdropUrl, image)
            setImage(movie.posterUrl, poster)
        }
    }

    private fun setImage(url: String, imageView: ImageView) {
        Glide.with(requireContext())
            .load(url)
            .placeholder(R.drawable.ic_movie)
            .into(imageView)
    }

    private fun prepareToolbar() {
        (requireActivity() as AppCompatActivity).run {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(true)
                setHomeButtonEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) { }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> false
        }
    }

    companion object {
        private const val MOVIE_ID = "movie_id"

        fun newInstance(movieId: Int) = MovieDetailsFragment().apply {
            arguments = bundleOf(
                MOVIE_ID to movieId
            )
        }
    }
}
