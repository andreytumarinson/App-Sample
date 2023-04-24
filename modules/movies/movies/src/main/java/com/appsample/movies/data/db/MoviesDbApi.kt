package com.appsample.movies.data.db

import androidx.room.*
import com.appsample.movies.data.db.models.DbMovieItemDetails

@Dao
internal interface MoviesDbApi {
    @Query("SELECT * FROM movies")
    fun getMovies(): List<DbMovieItemDetails>

    @Transaction
    fun replaceMovies(movies: List<DbMovieItemDetails>) {
        deleteMovies()
        insertMovies(movies)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<DbMovieItemDetails>)

    @Query("DELETE FROM movies")
    fun deleteMovies()

    @Query("SELECT * FROM movies WHERE id IS :movieId")
    fun getMovie(movieId: Int): DbMovieItemDetails?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: DbMovieItemDetails)
}
