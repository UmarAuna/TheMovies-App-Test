package com.movies.themoviestestapp.repository.converters

import androidx.room.TypeConverter

class GenreConverter {
    @TypeConverter
    fun fromCoordinates(list: List<Int>?): String {
        return list?.joinToString(separator = ",") { it.toString() } ?: ""
    }

    @TypeConverter
    fun toCoordinates(string: String?): List<Int> {
        return ArrayList(string?.split(",")?.mapNotNull { it.toIntOrNull() } ?: emptyList())
    }
}