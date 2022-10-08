package com.movies.themoviestestapp.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.movies.themoviestestapp.home.models.Results

@Dao
interface TheMoviesDAO {

    @Query("SELECT * FROM Results")
    fun findAll(): List<Results>

    @Query("SELECT * FROM Results")
    fun getAllData(): LiveData<List<Results>>

    @Query("DELETE FROM Results")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(movies: List<Results>)
}
