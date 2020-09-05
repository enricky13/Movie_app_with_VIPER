package com.example.movie_viper_app.mainModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.movie_viper_app.R
import com.example.movie_viper_app.entity.MovieDataWithResults
import com.example.movie_viper_app.entity.MovieNetworkSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), MainContract.View{
    private var presenter : MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)

        MovieNetworkSingleton.retrofitService
            .getNowPlayingMovies("07d84b920daba19e3cd63fda9637a04d")
            .enqueue(object : Callback<MovieDataWithResults> {
                override fun onFailure(call: Call<MovieDataWithResults>, t: Throwable) {
                    Log.d("FINDME", t.toString())
                }

                override fun onResponse(
                    call: Call<MovieDataWithResults>,
                    response: Response<MovieDataWithResults>
                ) {
                    response.body()?.results?.forEach {
                        Log.d("FINDME", it.title)
                    }
                    if (response.body()?.results.isNullOrEmpty()) Log.d("FINDME", "No results returned back")
                }
            })
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
}