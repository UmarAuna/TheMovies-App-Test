package com.movies.themoviestestapp.home.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movies.themoviestestapp.R
import com.movies.themoviestestapp.home.models.Results
import com.movies.themoviestestapp.repository.local.TheMoviesDAO
import com.movies.themoviestestapp.repository.repo.TheMoviesRepository
import com.movies.themoviestestapp.utils.NetworkManager
import com.movies.themoviestestapp.utils.Paging
import com.movies.themoviestestapp.utils.Resource
import com.movies.themoviestestapp.utils.emit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TheMoviesListFragmentViewModel : ViewModel(), KoinComponent {

    private val theMoviesRepo: TheMoviesRepository by inject()
    private val context: Context by inject()
    private val networkManager: NetworkManager by inject()
    private val theMoviesDAO: TheMoviesDAO by inject()

    private val _popularMovies = MutableLiveData<Resource<List<Results>>>()
    val observePopularMovies
        get() = _popularMovies.emit()

    val observedCachedData
        get() = Transformations.map(theMoviesDAO.getAllData()) {
            it
        }

    val observedSearchedData
        get() = Transformations.map(theMoviesDAO.getSortedData("Coco")) {
            it
        }

    private val paging = Paging()

    fun init() {
        paging.reset()
        getPopularMovies()
    }

    private fun getPopularMovies(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _popularMovies.postValue(Resource.loading(null))
                if (networkManager.isConnected(context)) {
                    theMoviesRepo.getPopularMovies(page).let {
                        if (it.isSuccessful) {
                            _popularMovies.postValue(Resource.success(it.body()?.results))
                            paging.nextPage++
                            paging.totalPages = PAGE_SIZE + 1
                        } else {
                            _popularMovies.postValue(Resource.error(it.message().toString(), null))
                        }
                    }
                } else {
                    _popularMovies.postValue(Resource.error(context.getString(R.string.internet_not_available), null))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onLastItemScrolled() {
        paging.performIfNotLastItem {
            getPopularMovies(paging.nextPage)
        }
    }

    companion object {
        const val PAGE_SIZE = 500
    }
}
