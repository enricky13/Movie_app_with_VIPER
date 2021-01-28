package com.example.movie_viper_app.mainModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_viper_app.R
import com.example.movie_viper_app.adapter.MovieAdapter
import com.example.movie_viper_app.databinding.ActivityMainBinding
import com.example.movie_viper_app.enums.CallSelector


class MainActivity : AppCompatActivity(), MainContract.View{
    private var presenter : MainPresenter? = null
    private var linearLayoutManager : LinearLayoutManager? = null
    private var homeBinding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)
        homeBinding?.recyclerviewPopularMovies?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        homeBinding?.recyclerviewTopMovies?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        homeBinding?.recyclerviewNowPlayingMovies?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        presenter = MainPresenter(this)
        presenter?.onActivityCreated()

    }

    override fun setupRecyclerView(selector: CallSelector, adapter: MovieAdapter) {
        when (selector){
            CallSelector.MOST_POPULAR -> {
                homeBinding?.recyclerviewPopularMovies?.adapter = adapter
                homeBinding?.popularMoviesPb?.visibility = ProgressBar.GONE
            }

            CallSelector.TOP_RATED -> {
                homeBinding?.recyclerviewTopMovies?.adapter = adapter
                homeBinding?.topMoviePb?.visibility = ProgressBar.GONE
            }

            CallSelector.NOW_PLAYING -> {
                homeBinding?.recyclerviewNowPlayingMovies?.adapter = adapter
                homeBinding?.nowPlayingPb?.visibility = ProgressBar.GONE
            }
        }
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
}