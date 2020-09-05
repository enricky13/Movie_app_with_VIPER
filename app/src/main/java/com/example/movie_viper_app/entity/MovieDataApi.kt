package com.example.movie_viper_app.entity

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDataApi {
    @GET(TOP_MOVIES)
    fun getTopMovies(@Query(API_KEY) apiKey : String) : Call<MovieDataWithResults>
    @GET(NOW_PLAYING)
    fun getNowPlayingMovies(@Query(API_KEY) apiKey: String) : Call<MovieDataWithResults>
    @GET(POPULAR_MOVIES)
    fun getPopularMovies(@Query(API_KEY) apiKey: String) : Call<MovieDataWithResults>

    companion object {
        const val API_KEY = "api_key"
        const val TOP_MOVIES = "3/movie/top_rated"
        const val NOW_PLAYING = "3/movie/now_playing"
        const val POPULAR_MOVIES = "3/movie/popular"
    }
}