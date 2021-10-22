package com.example.killerjoke.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.killerjoke.data.entities.JokeForDB

@Dao
interface JokeDao {
    @Query("SELECT * FROM `jokes-db` ")
    fun loadAllJokes(): LiveData<List<JokeForDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jokeForDB: JokeForDB)

    @Query("DELETE FROM `jokes-db`")
    suspend fun deleteAll()
}
