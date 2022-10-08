package com.movies.themoviestestapp.koin

import com.movies.themoviestestapp.repository.remote.TheMoviesApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideTheMoviesApi(retrofit: Retrofit): TheMoviesApiService {
        return retrofit.create(TheMoviesApiService::class.java)
    }

    single { provideTheMoviesApi(get()) }
}