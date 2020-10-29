package com.example.movie_viper_app.detailModule

import com.example.movie_viper_app.entity.MovieTrailerResults
import com.example.movie_viper_app.entity.Results

interface DetailContract {
    interface View {
        fun displayMovieImage(image: String)
        fun displayMovieName(title: String)
        fun displayMovieRating(rating: Float)
        fun displayMovieDescription(description: String)
        fun displayMovieTrailer(youtubeLink : String)
    }

    interface Interactor {
        fun fetchMovieTrailerData(movieId: Int, interactorOutput: InteractorOutPut)
    }

    interface InteractorOutPut {
        fun onSuccess(result: MovieTrailerResults?)
        fun onFailure(message: String)
    }

    interface Presenter {
        fun onActivityCreated()
        fun getIntentData(results : Results?)
        fun trailerButtonClicked()
    }

    interface Router {
        //NO-OP
    }
}