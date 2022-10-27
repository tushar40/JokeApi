package com.example.jokeapi.data.database.roomDatabase.dao

import androidx.room.*
import com.example.jokeapi.data.model.JokeDTO
import com.example.jokeapi.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface JokesDao {
    @Query("SELECT * FROM JokeDTO")
    fun getAll(): Flow<List<JokeDTO>>

    @Query("SELECT * FROM JokeDTO")
    fun getAllSync(): List<JokeDTO>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(joke: JokeDTO)

    @Transaction
    suspend fun insertAndDelete(joke: JokeDTO, limit: Int = Constants.JOKES_LIMIT) {
        val jokes = getAllSync()
        if (jokes.size >= limit) {
            delete(jokes.first().description)
        }
        insert(joke)
    }

    @Query("DELETE from JokeDTO WHERE description = :desc")
    suspend fun delete(desc: String)
}