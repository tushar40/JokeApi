package com.example.jokeapi.data.database.roomDatabase.dao

import androidx.room.*
import com.example.jokeapi.data.model.MovieDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Query("SELECT * FROM MovieDTO")
    fun getAll(): Flow<List<MovieDTO>>

    @Query("SELECT * FROM MovieDTO where id = :id")
    fun getMovie(id: Long): MovieDTO

    @Query("SELECT * FROM MovieDTO")
    fun getAllSync(): List<MovieDTO>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: MovieDTO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg movies: MovieDTO)

    @Query("DELETE from MovieDTO WHERE id = :id")
    suspend fun delete(id: Long)

    @Transaction
    suspend fun updatePlaylist(id: Long, playlist: String) {
        val movie = getMovie(id)
        movie.playlist = playlist
        insert(movie)
    }
}