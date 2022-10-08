package com.movies.themoviestestapp.koin

import com.movies.themoviestestapp.home.viewmodels.MainActivityViewModel
import com.movies.themoviestestapp.home.viewmodels.TheMoviesListFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainActivityViewModel() }
    viewModel { TheMoviesListFragmentViewModel() }
}
