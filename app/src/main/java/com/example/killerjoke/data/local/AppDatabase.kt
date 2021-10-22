package com.example.killerjoke.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.killerjoke.data.entities.JokeForDB
import com.example.killerjoke.other.Constants.NAME_DATABASE

@Database(entities = [JokeForDB::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): JokeDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, NAME_DATABASE)
                .fallbackToDestructiveMigration()
                .build()
    }
}
