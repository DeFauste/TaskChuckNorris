package com.example.killerjoke.ui.model

import android.util.Log
import androidx.lifecycle.*
import com.example.killerjoke.data.entities.Joke
import com.example.killerjoke.data.entities.JokeForDB
import com.example.killerjoke.data.local.JokeRepository
import com.example.killerjoke.network.ApiService
import com.example.killerjoke.network.ServiceApi
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    private val repository: JokeRepository,
    private val apiService: ApiService = ServiceApi.retrofitService
) : ViewModel() {

    val allJokes: LiveData<List<JokeForDB>> = repository.allJokes

    fun insert(jokeForDB: JokeForDB) = viewModelScope.launch {
        repository.insert(jokeForDB)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    var number: Int = 0

    val jokes = MutableLiveData<List<Joke>>()

    suspend fun getJokes() {
        try {
//            jokes.postValue(apiService.getJokes(number).value)
            apiService.getJokes(number).value.forEach {
                if (it.joke != null)
                    insert(JokeForDB(it.joke))
            }
        } catch (e: Exception) {
            Log.d("Network", e.toString())
        }
    }
}

class JokeViewModelFactory(
    private val repository: JokeRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
