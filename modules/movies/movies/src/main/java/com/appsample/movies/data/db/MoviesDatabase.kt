package com.appsample.movies.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appsample.movies.data.db.models.DbMovieItemDetails

@Database(entities = [DbMovieItemDetails::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    internal abstract fun moviesDbApi(): MoviesDbApi
}
