package com.example.movie_viper_app.detailModule

import com.bumptech.glide.Glide
import com.example.movie_viper_app.entity.Results

interface DetailContract {
    interface View {
        fun displayMovieImage(image: String)
        fun displayMovieName(title: String)
        fun displayMovieRating(rating: Float)
        fun displayMovieDescription(description: String)
    }

    interface Interactor {

    }

    interface Presenter {
        fun onActivityCreated()
        fun getIntentData(results : Results?)
    }

    interface Router {

    }
}