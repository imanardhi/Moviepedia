package com.imanardhi.moviepedia.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.imanardhi.moviepedia.data.model.GenreResponse
import com.imanardhi.moviepedia.databinding.ListGenresBinding

class GenresAdapter(private val navigate: (GenreResponse) -> Unit) :
    PagingDataAdapter<GenreResponse, GenresAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ListGenresBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var genreResponse: GenreResponse? = null

        fun bind(genreResponse: GenreResponse, position: Int) {
            this.genreResponse = genreResponse
            binding.apply {
                genre = genreResponse

                binding.root.setOnClickListener {
                    genreResponse?.let {
                        navigate.invoke(it)
                    }
                }
                executePendingBindings()
                executePendingBindings()
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListGenresBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private class DiffCallback : DiffUtil.ItemCallback<GenreResponse>() {
        override fun areItemsTheSame(oldItem: GenreResponse, newItem: GenreResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GenreResponse, newItem: GenreResponse): Boolean {
            return oldItem == newItem
        }
    }
}