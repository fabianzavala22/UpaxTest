package com.example.upaxtest.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.upaxtest.model.MovieModel
import com.example.upaxtest.model.Movies

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(list: List<MovieModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieSingle(movie: MovieModel)

    /*@Query("SELECT * FROM MovieModel")
    fun getAllMovies(): PagingSource<Int, MovieModel>*/

    @Query("SELECT * FROM MovieModel")
    fun getAllMovies(): List<MovieModel>

    @Query("DELETE FROM MovieModel")
    suspend fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(list: List<MoviesRemoteKey>)

    @Query("SELECT * FROM MoviesRemoteKey WHERE id = :id")
    suspend fun getAllREmoteKey(id: String): MoviesRemoteKey?

    @Query("DELETE FROM MoviesRemoteKey")
    suspend fun deleteAllRemoteKeys()
}