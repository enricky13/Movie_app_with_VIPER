package com.example.movie_viper_app.detailModule

import com.bumptech.glide.Glide
import com.example.movie_viper_app.entity.Results

class DetailPresenter(private val view : DetailContract.View?) : DetailContract.Presenter{

    override fun onActivityCreated() {
    }

    override fun getIntentData(results: Results?) {
        view?.displayMovieName(results?.title ?: "No Title Found")
        view?.displayMovieImage(results?.image ?: "No Image Found")
        view?.displayMovieDescription(results?.overview ?: "No Description Found")
        view?.displayMovieRating(results?.voteAverage?.toFloat() ?: 0f)
    }
}