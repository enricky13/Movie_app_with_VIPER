package com.example.movie_viper_app.entity

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieNetworkSingleton {
    private const val BASE_URL = "https://api.themoviedb.org/"

    private fun retrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val retrofitService : MovieDataApi by lazy {
        retrofit().create(MovieDataApi::class.java)
    }
}