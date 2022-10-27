package com.example.jokeapi.data.database.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jokeapi.BuildConfig
import com.example.jokeapi.MainApplication
import com.example.jokeapi.data.database.roomDatabase.dao.JokesDao
import com.example.jokeapi.data.model.JokeDTO

/**
 * The Room database for this app
 */
const val DATABASE_NAME = "app-db"

@Database(
        entities = [JokeDTO::class],
        version = BuildConfig.VERSION_CODE,
        exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun jokesDao(): JokesDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(MainApplication.INSTANCE).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}