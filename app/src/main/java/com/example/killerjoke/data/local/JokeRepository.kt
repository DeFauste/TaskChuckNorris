package com.example.killerjoke.data.local

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.killerjoke.data.entities.JokeForDB

class JokeRepository(private val jokeDao: JokeDao) {

    val allJokes: LiveData<List<JokeForDB>> = jokeDao.loadAllJokes()

    @WorkerThread
    suspend fun insert(jokeForDB: JokeForDB) {
        jokeDao.insert(jokeForDB)
    }

    @WorkerThread
    suspend fun deleteAll() {
        jokeDao.deleteAll()
    }
}
