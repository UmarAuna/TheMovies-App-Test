package com.movies.themoviestestapp.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.movies.themoviestestapp.databinding.FragmentTheMoviesListBinding
import com.movies.themoviestestapp.home.adapter.TheMoviesAdapter
import com.movies.themoviestestapp.home.models.Results
import com.movies.themoviestestapp.home.viewmodels.MainActivityViewModel
import com.movies.themoviestestapp.home.viewmodels.TheMoviesListFragmentViewModel
import com.movies.themoviestestapp.utils.Status
import com.movies.themoviestestapp.utils.customviews.LoadingDialog
import com.movies.themoviestestapp.utils.setOnLastItemScrolled
import com.movies.themoviestestapp.utils.showToast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TheMoviesListFragment : Fragment() {

    private lateinit var binding: FragmentTheMoviesListBinding
    private val parentViewModel: MainActivityViewModel by sharedViewModel()
    private val viewModel: TheMoviesListFragmentViewModel by viewModel()
    private lateinit var theMovieDbAdapter: TheMoviesAdapter
    private var loadingDialog: LoadingDialog? = null
    private val directions by lazy { TheMoviesListFragmentDirections }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTheMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = LoadingDialog.newInstance(requireContext())
        parentViewModel.setTitle("Home")
        parentViewModel.showBackButton(false)
        viewModel.init()

        binding.refresh.setOnClickListener {
            viewModel.init()
        }

        theMovieDbAdapter = TheMoviesAdapter(
            arrayListOf(),
            TheMoviesAdapter.ClickListenerMovie { movie ->
                val direction = directions.actionTheMoviesListFragmentToTheMoviesDetailFragment(movie)
                findNavController().navigate(direction)
            }
        )
        theMovieDbAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.moviesRecyclerView.layoutManager = LinearLayoutManager(context)
        theMovieDbAdapter.hasStableIds()
        binding.moviesRecyclerView.adapter = theMovieDbAdapter
        binding.moviesRecyclerView.setOnLastItemScrolled {
            viewModel.onLastItemScrolled()
        }
       /* viewModel.observedSearchedData.observe(viewLifecycleOwner) {
            renderList(it)
        }*/

        viewModel.observePopularMovies.observe(viewLifecycleOwner) { movies ->
            when (movies.status) {
                Status.SUCCESS -> {
                    loadingDialog?.dismiss()
                    movies.data?.let { renderList(it) }
                }
                Status.LOADING -> loadingDialog?.show()
                Status.ERROR -> {
                    getCachedData()
                    loadingDialog?.dismiss()
                    showToast("${movies.message}")
                }
            }
        }
    }

    private fun getCachedData() {
        viewModel.observedCachedData.observe(viewLifecycleOwner) { movies ->
            renderListOffline(movies)
        }
    }

    private fun renderList(makeup: List<Results>) {
        theMovieDbAdapter.addData(makeup)
        // theMovieDbAdapter.submitList(makeup)
        theMovieDbAdapter.notifyDataSetChanged()
    }

    private fun renderListOffline(makeup: List<Results>) {
        theMovieDbAdapter.addDataOffline(makeup)
        // theMovieDbAdapter.submitList(makeup)
        theMovieDbAdapter.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        loadingDialog?.dismiss()
    }

    companion object {
        fun newInstance() =
            TheMoviesListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
