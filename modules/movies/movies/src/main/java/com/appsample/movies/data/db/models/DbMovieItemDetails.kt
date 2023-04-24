package com.appsample.movies.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "movies")
internal data class DbMovieItemDetails(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "backdrop_path") val backdropUrl: String,
    @ColumnInfo(name = "poster_path") val posterUrl: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "budget") val budget: Int,
    @ColumnInfo(name = "vote_average") val rating: Float,
    @ColumnInfo(name = "release_date") val releaseDate: String,
)
