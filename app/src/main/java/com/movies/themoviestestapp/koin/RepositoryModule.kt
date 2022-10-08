package com.movies.themoviestestapp.koin

import com.movies.themoviestestapp.repository.repo.TheMoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { TheMoviesRepository() }
}