package com.movies.themoviestestapp.repository.remote

import com.movies.themoviestestapp.home.models.MovieLists
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMoviesApiService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieLists>
}