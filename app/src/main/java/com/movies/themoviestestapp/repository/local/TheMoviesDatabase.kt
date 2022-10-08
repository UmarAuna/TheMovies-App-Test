package com.movies.themoviestestapp.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.movies.themoviestestapp.home.models.MovieLists
import com.movies.themoviestestapp.home.models.Results
import com.movies.themoviestestapp.repository.converters.AbstractConverter
import com.movies.themoviestestapp.repository.converters.GenreConverter

@Database(
    entities = [
        MovieLists::class,
        Results::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    GenreConverter::class,
    ResultsConverter::class
)
abstract class TheMoviesDatabase : RoomDatabase() {
    abstract val theMoviesDAO: TheMoviesDAO
}

private class ResultsConverter : AbstractConverter<Results>(Results::class, Array<Results>::class)
