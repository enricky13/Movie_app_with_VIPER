package com.example.movie_viper_app.mainModule

import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_viper_app.adapter.MovieAdapter
import com.example.movie_viper_app.entity.CallSelector
import com.example.movie_viper_app.entity.Results

class MainPresenter(private var view: MainContract.View?) : MainContract.Presenter, MainContract.InteractorOutPut{
    private var interactor : MainContract.Interactor = MainInteractor()
    private var router : MainContract.Router = MainRouter()
    private lateinit var adapter : MovieAdapter

    override fun onActivityCreated() {
        interactor.fetchData(this)
    }

    override fun onDestroy() {
        view = null
    }

    override fun onSuccess(result : List<Results>, selector: CallSelector) {
        adapter = MovieAdapter(result, selector)
        view?.setupRecyclerView(selector, adapter)
        when (selector){
            CallSelector.NOW_PLAYING -> {
                result.forEach {
                    Log.d("FINDME", "Now Playing: ${it.image}")
                }
            }

            CallSelector.TOP_RATED -> {
                result.forEach {
                    Log.d("FINDME", "Top Rated: ${it.image}")
                }
            }

            CallSelector.MOST_POPULAR -> {
                result.forEach {
                    Log.d("FINDME", "Most Popular: ${it.image}")
                }
            }
        }
    }

    override fun onFailure(message: String) {
        Log.d("FINDME", message)
    }

}