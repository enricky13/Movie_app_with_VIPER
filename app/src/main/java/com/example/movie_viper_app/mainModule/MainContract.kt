package com.example.movie_viper_app.mainModule

import com.example.movie_viper_app.adapter.MovieAdapter
import com.example.movie_viper_app.enums.CallSelector
import com.example.movie_viper_app.entity.Results

interface MainContract {
    interface View{
        fun setupRecyclerView(selector: CallSelector, adapter: MovieAdapter)
    }
    interface Interactor{
        fun fetchData(interactorOutPut: InteractorOutPut)
    }
    interface InteractorOutPut{
        fun onSuccess(result : List<Results>, selector: CallSelector)
        fun onFailure(message : String)
    }
    interface Presenter{
        fun onActivityCreated()
        fun onDestroy()
        interface MovieClickListener {
            fun onMovieClick(result: Results)
        }
    }
    interface Router{
        fun goToDetailActivity(results: Results)
    }
}