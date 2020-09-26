package com.example.movie_viper_app.mainModule

import android.app.Activity
import android.util.Log
import com.example.movie_viper_app.adapter.MovieAdapter
import com.example.movie_viper_app.enums.CallSelector
import com.example.movie_viper_app.entity.Results

class MainPresenter(private var view: MainContract.View?) : MainContract.Presenter,
    MainContract.InteractorOutPut {
    private var interactor: MainContract.Interactor? = MainInteractor()
    private var router: MainContract.Router? = MainRouter(view as? Activity)

    override fun onActivityCreated() {
        interactor?.fetchData(this)
    }

    override fun onSuccess(result: List<Results>, selector: CallSelector) {
        view?.setupRecyclerView(
            selector,
            MovieAdapter(result, object : MainContract.Presenter.MovieClickListener {
                override fun onMovieClick(result: Results) {
                    router?.goToDetailActivity(result)
                }
            })
        )
        when (selector) {
            CallSelector.NOW_PLAYING -> {
                result.forEach {
                    Log.d("FINDME", "Now Playing Movie ID: ${it.movieId}")
                }
            }

            CallSelector.TOP_RATED -> {
                result.forEach {
                    Log.d("FINDME", "Top Rated: ${it.title}")
                }
            }

            CallSelector.MOST_POPULAR -> {
                result.forEach {
                    Log.d("FINDME", "Most Popular: ${it.title}")
                }
            }
        }
    }

    override fun onFailure(message: String) {
        Log.d("FINDME", message)
    }

    override fun onDestroy() {
        view = null
        interactor = null
        router = null
    }
}