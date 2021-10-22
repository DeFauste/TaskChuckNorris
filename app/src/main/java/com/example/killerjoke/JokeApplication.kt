package com.example.killerjoke

import android.app.Application
import com.example.killerjoke.data.local.AppDatabase
import com.example.killerjoke.data.local.JokeRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class JokeApplication : Application() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy {
        JokeRepository(database.characterDao()).also {
            GlobalScope.launch {
                it.deleteAll()
            }
        }
    }
}
