package com.example.killerjoke.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.killerjoke.other.Constants.NAME_DATABASE
import java.io.Serializable

@Entity(tableName = NAME_DATABASE)
class JokeForDB(val textJoke: String) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}
