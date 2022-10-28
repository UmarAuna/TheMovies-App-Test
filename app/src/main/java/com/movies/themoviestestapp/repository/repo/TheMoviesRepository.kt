package com.movies.themoviestestapp.repository.repo

import com.movies.themoviestestapp.BuildConfig
import com.movies.themoviestestapp.home.models.MovieLists
import com.movies.themoviestestapp.repository.local.TheMoviesDAO
import com.movies.themoviestestapp.repository.remote.TheMoviesApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

class TheMoviesRepository : KoinComponent {

    private val theMoviesApiService: TheMoviesApiService by inject()
    private val theMoviesDAO: TheMoviesDAO by inject()

    suspend fun getPopularMovies(page: Int): Response<MovieLists> {
        return try {
            withContext(Dispatchers.IO) {
                val apiKey = BuildConfig.API_KEY
                val popularMovies = theMoviesApiService.getPopularMovies(apiKey, page)
                popularMovies.body()?.let {
                    // theMoviesDAO.deleteAll()
                    theMoviesDAO.add(it.results)
                }
                popularMovies
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}
