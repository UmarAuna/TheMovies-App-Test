package com.movies.themoviestestapp.home.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity
data class MovieLists(
    @PrimaryKey
    @SerializedName("room_id")
    val roomId: Int?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<Results>,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
) : Parcelable