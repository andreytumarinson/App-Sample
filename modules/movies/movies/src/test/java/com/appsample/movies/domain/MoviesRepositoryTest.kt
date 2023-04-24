package com.appsample.movies.domain

import com.appsample.common.models.AppException
import com.appsample.common.models.Error
import com.appsample.movies.data.api.MoviesServerApi
import com.appsample.movies.data.db.MoviesDbApi
import com.appsample.movies.data.db.models.DbMovieItemDetails
import com.appsample.movies.domain.repository.MoviesRepository
import com.appsample.movies.domain.repository.MoviesRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.kotlin.*
import java.io.IOException

class MoviesRepositoryTest {

    @Test
    fun getMovieFromCloud_ok() = runTest {
        createEnvironment().run {
            val result = moviesRepository.getMovieDetails(MOVIE_ID)

            verify(mockCloudProvider).getMovieDetails(MOVIE_ID)
            verify(mockDbProvider).insertMovie(testMovieDb)
            verify(mockDbProvider).getMovie(MOVIE_ID)

            assertEquals(testMovie, result)
        }
    }

    @Test
    fun getAdsFromCloud_fail_fromDb_ok() = runTest {
        createEnvironment(serverException = AppException(Error.NO_CONNECTION)).run {
            val result = moviesRepository.getMovieDetails(MOVIE_ID)

            verify(mockCloudProvider).getMovieDetails(MOVIE_ID)
            verify(mockDbProvider, never()).insertMovie(testMovieDb)
            verify(mockDbProvider).getMovie(MOVIE_ID)

            assertEquals(testMovie, result)
        }
    }

    @Test
    fun getAdsFromCloud_fail_fromDb_null() = runTest {
        createEnvironment(
            serverException = IOException(),
            dbAnswer = null
        ).run {
            val exception = assertThrows<AppException> {
                moviesRepository.getMovieDetails(MOVIE_ID)
            }
            assertEquals(Error.NO_CONNECTION, exception.error)

            verify(mockCloudProvider).getMovieDetails(MOVIE_ID)
            verify(mockDbProvider, never()).insertMovie(testMovieDb)
            verify(mockDbProvider).getMovie(MOVIE_ID)
        }
    }

    @Test
    fun getAdsFromCloud_ok_fromDb_null() = runTest {
        createEnvironment(dbAnswer = null).run {
            val exception = assertThrows<AppException> {
                moviesRepository.getMovieDetails(MOVIE_ID)
            }
            assertEquals(Error.NO_CONTENT, exception.error)

            verify(mockCloudProvider).getMovieDetails(MOVIE_ID)
            verify(mockDbProvider).insertMovie(testMovieDb)
            verify(mockDbProvider).getMovie(MOVIE_ID)
        }
    }

    private class Environment(
        val mockCloudProvider: MoviesServerApi,
        val mockDbProvider: MoviesDbApi,
        val moviesRepository: MoviesRepository,
    )

    private fun createEnvironment(
        serverException: Exception? = null,
        dbAnswer: DbMovieItemDetails? = testMovieDb,
    ): Environment {
        val mockCloudProvider: MoviesServerApi = mock {
            if (serverException == null) {
                onBlocking { getMovieDetails(anyInt()) } doReturn testMovieServer
            } else {
                onBlocking { getMovieDetails(anyInt()) } doAnswer { throw serverException }
            }
        }
        val mockDbProvider: MoviesDbApi =
            mock { onBlocking { getMovie(anyInt()) } doReturn dbAnswer }
        return Environment(
            mockCloudProvider = mockCloudProvider,
            mockDbProvider = mockDbProvider,
            moviesRepository = MoviesRepositoryImpl(
                mockCloudProvider,
                mockDbProvider,
                mock(),
                mock { on { toApp(any()) } doReturn testMovie },
            ),
        )
    }

    private companion object {
        private const val MOVIE_ID = 123
    }
}

