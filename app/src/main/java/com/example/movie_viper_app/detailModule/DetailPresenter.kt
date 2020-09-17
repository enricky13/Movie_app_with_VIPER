package com.example.movie_viper_app.detailModule

import android.util.Log
import com.example.movie_viper_app.entity.MovieTrailerResults
import com.example.movie_viper_app.entity.Results

class DetailPresenter(private val view : DetailContract.View?) : DetailContract.Presenter{
    private var interactor : DetailInteractor? = DetailInteractor()
    private var results : Results? = null

    override fun onActivityCreated() {
        //No-op
    }

    override fun getIntentData(results: Results?) {
        this.results = results
        view?.displayMovieName(results?.title ?: "No Title Found")
        view?.displayMovieImage(results?.image ?: "No Image Found")
        view?.displayMovieDescription(results?.overview ?: "No Description Found")
        view?.displayMovieRating(results?.voteAverage?.toFloat() ?: 0f)
    }

    override fun trailerButtonClicked() {
        interactor?.fetchMovieTrailerData(results?.movieId ?: -1, object : DetailContract.InteractorOutPut {
            override fun onSuccess(result: MovieTrailerResults?) {
                view?.displayMovieTrailer(result?.youtubeKey ?: "No Key")
            }

            override fun onFailure(message: String) {
                Log.d("FINDME", message)
            }
        })
    }
}