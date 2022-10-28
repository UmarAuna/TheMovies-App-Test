package com.movies.themoviestestapp.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.movies.themoviestestapp.R
import com.movies.themoviestestapp.databinding.ItemsMovieBinding
import com.movies.themoviestestapp.home.models.Results
import com.movies.themoviestestapp.utils.Constant
import com.movies.themoviestestapp.utils.loadImageWithGlide
import kotlin.math.roundToInt

class TheMoviesAdapter(
    private var items: ArrayList<Results>,
    private val clickListenerMovie: ClickListenerMovie
) : PagingDataAdapter<Results, TheMoviesAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Results, clickListenerMovie: ClickListenerMovie) {
            binding.rating.textProgress.text = item.voteAverage.toString()
            binding.rating.ratingProgressbar.progress = item.voteAverage?.roundToInt()!!
            binding.titleTextView.text = item.title
            binding.releaseDateTextView.text = item.releaseDate
            binding.overviewTextView.text = item.overview
            binding.iconImageView.loadImageWithGlide(Constant.imagePrefix + item.posterPath, R.drawable.no_image)

            binding.cardView.setOnClickListener {
                clickListenerMovie.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], clickListenerMovie)
    }

    override fun getItemCount() = items.size

    fun addData(list: List<Results>) {
        val previousItemsSize: Int = list.size
        // items.clear()
        items.addAll(list)
        notifyItemRangeChanged(previousItemsSize, list.size)
    }

    fun addDataOffline(list: List<Results>) {
        items.clear()
        items.addAll(list)
    }

    class ClickListenerMovie(val clickListenerMovie: (results: Results) -> Unit) {
        fun onClick(results: Results) = clickListenerMovie(results)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Results> = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem == newItem
            }
        }
    }
}
