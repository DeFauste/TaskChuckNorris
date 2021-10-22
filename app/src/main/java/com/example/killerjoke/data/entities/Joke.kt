package com.example.killerjoke.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Jokes(
    @Json(name = "type") val type: String?,
    @Json(name = "value") val value: List<Joke>
)

data class Joke(
    @Json(name = "id") val id: Int?,
    @Json(name = "joke") val joke: String?,
)
