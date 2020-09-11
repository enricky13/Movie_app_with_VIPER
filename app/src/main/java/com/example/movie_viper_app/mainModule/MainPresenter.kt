package com.example.movie_viper_app.mainModule

import android.app.Activity
import android.util.Log
import com.example.movie_viper_app.adapter.MovieAdapter
import com.example.movie_viper_app.entity.CallSelector
import com.example.movie_viper_app.entity.Results

class MainPresenter(private var view: MainContract.View?) : MainContract.Presenter,
    MainContract.InteractorOutPut {
    private var interactor: MainContract.Interactor? = MainInteractor()
    private var router: MainContract.Router? = MainRouter(view as? Activity)

    override fun onActivityCreated() {
        interactor?.fetchData(this)
    }

    override fun onSuccess(results: List<Results>, selector: CallSelector) {
        view?.setupRecyclerView(
            selector,
            MovieAdapter(results, object : MainContract.Presenter.MovieClickListener {
                override fun onMovieClick(result: Results) {
                    router?.goToDetailActivity(result)
                }
            })
        )
        when (selector) {
            CallSelector.NOW_PLAYING -> {
                results.forEach {
                    Log.d("FINDME", "Now Playing: ${it.title}")
                }
            }

            CallSelector.TOP_RATED -> {
                results.forEach {
                    Log.d("FINDME", "Top Rated: ${it.title}")
                }
            }

            CallSelector.MOST_POPULAR -> {
                results.forEach {
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