package com.example.movie_viper_app.detailModule

import android.util.Log
import com.example.movie_viper_app.entity.MovieNetworkSingleton
import com.example.movie_viper_app.entity.MovieTrailerWithResults
import com.example.movie_viper_app.mainModule.MainInteractor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailInteractor : DetailContract.Interactor {

    override fun fetchMovieTrailerData(movieId: Int, interactorOutput: DetailContract.InteractorOutPut) {
        MovieNetworkSingleton.retrofitService
            .getMovieTrailer(movieId.toString(), MainInteractor.API_KEY)
            .enqueue(object : Callback<MovieTrailerWithResults> {
                override fun onResponse(
                    call: Call<MovieTrailerWithResults>,
                    response: Response<MovieTrailerWithResults>
                ) {
                    interactorOutput.onSuccess(response.body()?.results?.find {
                        it.type == "trailer"
                        it.website == "YouTube"
                    })
                }

                override fun onFailure(call: Call<MovieTrailerWithResults>, t: Throwable) {
                    interactorOutput.onFailure(t.toString())
                }
            })
    }

}