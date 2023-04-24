package com.appsample.movies.ui.screens.list.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appsample.movies.domain.models.MovieItem
import com.appsample.movies.ui.R
import com.appsample.movies.ui.databinding.MovieItemBinding
import com.appsample.movies.ui.utils.FormatUtils
import com.bumptech.glide.Glide

class MoviesListAdapter(
    private val onItemClicked: (Int) -> Unit
) : ListAdapter<MovieItem, MoviesListAdapter.ViewHolder>(
    DiffCallback()
) {

    class ViewHolder(
        private val binding: MovieItemBinding,
        private val onClicked: ((Int) -> Unit),
        ): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { onClicked.invoke(movieItem.id) }
        }

        private lateinit var movieItem: MovieItem

        fun bind(item: MovieItem) {
            movieItem = item
            with(binding) {
                title.text = item.title
                rating.text = FormatUtils.formatRating(binding.root.context, item.rating)
                date.text = item.releaseDate

                Glide.with(binding.root.context)
                    .load(item.posterUrl)
                    .placeholder(R.drawable.ic_movie)
                    .into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }
    }

}
