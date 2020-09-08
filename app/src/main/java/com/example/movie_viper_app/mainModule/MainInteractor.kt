package com.example.movie_viper_app.mainModule

import com.example.movie_viper_app.entity.CallSelector
import com.example.movie_viper_app.entity.MovieDataWithResults
import com.example.movie_viper_app.entity.MovieNetworkSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainInteractor : MainContract.Interactor {

    override fun fetchData(interactorOutPut: MainContract.InteractorOutPut) {
        MovieNetworkSingleton.retrofitService
            .getPopularMovies(API_KEY)
            .enqueue(object : Callback<MovieDataWithResults> {
                override fun onResponse(
                    call: Call<MovieDataWithResults>,
                    response: Response<MovieDataWithResults>
                ) {
                    response.body()?.results?.let {
                        interactorOutPut.onSuccess(it, CallSelector.MOST_POPULAR)
                    }
                }

                override fun onFailure(call: Call<MovieDataWithResults>, t: Throwable) {
                    interactorOutPut.onFailure(t.toString())
                }
            })

        MovieNetworkSingleton.retrofitService
            .getNowPlayingMovies(API_KEY)
            .enqueue(object : Callback<MovieDataWithResults> {
                override fun onResponse(
                    call: Call<MovieDataWithResults>,
                    response: Response<MovieDataWithResults>
                ) {
                    response.body()?.results?.let {
                        interactorOutPut.onSuccess(it, CallSelector.NOW_PLAYING)
                    }
                }

                override fun onFailure(call: Call<MovieDataWithResults>, t: Throwable) {
                    interactorOutPut.onFailure(t.toString())
                }
            })

        MovieNetworkSingleton.retrofitService
            .getTopMovies(API_KEY)
            .enqueue(object : Callback<MovieDataWithResults> {
                override fun onResponse(
                    call: Call<MovieDataWithResults>,
                    response: Response<MovieDataWithResults>
                ) {
                    response.body()?.results?.let {
                        interactorOutPut.onSuccess(it, CallSelector.TOP_RATED)
                    }
                }

                override fun onFailure(call: Call<MovieDataWithResults>, t: Throwable) {
                    interactorOutPut.onFailure(t.toString())
                }
            })
    }

    companion object {
        val API_KEY = "07d84b920daba19e3cd63fda9637a04d"
    }

}