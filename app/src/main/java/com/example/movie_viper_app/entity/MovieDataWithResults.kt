package com.example.movie_viper_app.entity

import com.google.gson.annotations.SerializedName

data class MovieDataWithResults (
    val results : List<Results>
)

data class Results (
    @SerializedName("poster_path")
    val image : String?,
    val adult : Boolean,
    val overview : String,
    @SerializedName("release_date")
    val releaseDate : String,
    val title : String,
    val popularity : Number
)