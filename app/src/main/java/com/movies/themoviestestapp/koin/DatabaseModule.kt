package com.movies.themoviestestapp.koin

import android.app.Application
import androidx.room.Room
import com.movies.themoviestestapp.repository.local.TheMoviesDAO
import com.movies.themoviestestapp.repository.local.TheMoviesDatabase
import com.movies.themoviestestapp.utils.Constant
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): TheMoviesDatabase {
        return Room.databaseBuilder(application, TheMoviesDatabase::class.java, Constant.theMovieDb)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideTheMovieDAO(database: TheMoviesDatabase) = database.theMoviesDAO

    single { provideDatabase(androidApplication()) }
    single { provideTheMovieDAO(get()) }
}