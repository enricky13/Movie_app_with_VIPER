package com.example.movie_viper_app.entity

import com.google.gson.annotations.SerializedName

data class MovieTrailerWithResults(
    val results: List<MovieTrailerResults>
)

data class MovieTrailerResults (
    val id : String,
    @SerializedName("key")
    val youtubeKey : String,
    @SerializedName("name")
    val videoName : String,
    @SerializedName("site")
    val website : String,
    val type : String
)