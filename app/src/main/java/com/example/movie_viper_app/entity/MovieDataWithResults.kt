package com.example.movie_viper_app.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDataWithResults (
    val results : List<Results>
) : Serializable

data class Results (
    @SerializedName("poster_path")
    val image : String?,
    val adult : Boolean,
    val overview : String,
    @SerializedName("release_date")
    val releaseDate : String,
    val title : String,
    val popularity : Number,
    @SerializedName("vote_average")
    val voteAverage : Number,
    @SerializedName("id")
    val movieId : Int
) : Serializable