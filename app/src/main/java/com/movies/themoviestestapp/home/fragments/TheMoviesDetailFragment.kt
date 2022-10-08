package com.movies.themoviestestapp.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.movies.themoviestestapp.R
import com.movies.themoviestestapp.databinding.FragmentTheMoviesDetailBinding
import com.movies.themoviestestapp.databinding.ItemsGenreBinding
import com.movies.themoviestestapp.home.viewmodels.MainActivityViewModel
import com.movies.themoviestestapp.utils.Constant
import com.movies.themoviestestapp.utils.loadImageWithGlide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TheMoviesDetailFragment : Fragment() {

    private lateinit var binding: FragmentTheMoviesDetailBinding
    private val parentViewModel: MainActivityViewModel by sharedViewModel()
    private val args: TheMoviesDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTheMoviesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentViewModel.setTitle("Back")
        parentViewModel.showBackButton(true)

        binding.titleTextView.text = args.movies.title
        binding.releaseDateTextView.text = args.movies.releaseDate
        binding.ratingTextView.text = "${args.movies.voteAverage}/10"
        binding.overViewTextView.text = args.movies.overview
        binding.iconImageView.loadImageWithGlide(Constant.imagePrefix + args.movies.backdropPath, R.drawable.no_image)
        binding.listImageView.loadImageWithGlide(Constant.imagePrefix + args.movies.posterPath, R.drawable.no_image)
        args.movies.genreIds?.forEach { item ->
            val chip = getChips(item)
            binding.chipGroup.addView(chip)
        }
    }

    private fun getChips(item: Int?): View {
        val chip: ItemsGenreBinding = ItemsGenreBinding.inflate(
            layoutInflater,
            view?.parent as ViewGroup?,
            false
        )
        when (item) {
            28 -> chip.chip.text = "Action"
            12 -> chip.chip.text = "Adventure"
            16 -> chip.chip.text = "Animation"
            35 -> chip.chip.text = "Comedy"
            80 -> chip.chip.text = "Crime"
            99 -> chip.chip.text = "Documentary"
            18 -> chip.chip.text = "Drama"
            10751 -> chip.chip.text = "Family"
            14 -> chip.chip.text = "Fantasy"
            36 -> chip.chip.text = "History"
            27 -> chip.chip.text = "Horror"
            10402 -> chip.chip.text = "Music"
            9648 -> chip.chip.text = "Mystery"
            10749 -> chip.chip.text = "Romance"
            878 -> chip.chip.text = "Science Fiction"
            10770 -> chip.chip.text = "Tv Movie"
            53 -> chip.chip.text = "Thriller"
            10752 -> chip.chip.text = "War"
            37 -> chip.chip.text = "Western"
        }

        return chip.root
    }

    companion object {
        fun newInstance() =
            TheMoviesDetailFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
