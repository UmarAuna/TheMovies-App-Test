package com.movies.themoviestestapp

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.movies.themoviestestapp.home.models.Results
import com.movies.themoviestestapp.home.viewmodels.TheMoviesListFragmentViewModel
import com.movies.themoviestestapp.koin.apiModule
import com.movies.themoviestestapp.koin.networkModule
import com.movies.themoviestestapp.koin.repositoryModule
import com.movies.themoviestestapp.koin.viewModelModule
import com.movies.themoviestestapp.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TheMovieListViewModelTest : KoinTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val viewModel: TheMoviesListFragmentViewModel by inject()

    @Mock
    private lateinit var theMovieResponseObserver: Observer<Resource<List<Results>>>

    @Mock
    private lateinit var context: Application

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            androidContext(context)
            modules(
                viewModelModule,
                networkModule,
                repositoryModule,
                apiModule
            )
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `when calling for results then return loading`() {
        coroutineRule.runBlockingTest {
            viewModel.observePopularMovies.observeForever(theMovieResponseObserver)
            viewModel.init()
            Mockito.verify(theMovieResponseObserver).onChanged(Resource.loading(null))
        }
    }
}
