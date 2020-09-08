package com.example.movie_viper_app.mainModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_viper_app.R
import com.example.movie_viper_app.adapter.MovieAdapter
import com.example.movie_viper_app.entity.CallSelector


class MainActivity : AppCompatActivity(), MainContract.View{
    private var presenter : MainPresenter? = null
    private var linearLayoutManager : LinearLayoutManager? = null
    private var topMovieRv : RecyclerView? = null
    private var nowPlayingRv : RecyclerView? = null
    private var mostPopularRv : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        topMovieRv = findViewById(R.id.recyclerview_top_movies)
        nowPlayingRv = findViewById(R.id.recyclerview_now_playing_movies)
        mostPopularRv = findViewById(R.id.recyclerview_popular_movies)
        linearLayoutManager = LinearLayoutManager(this)
        presenter = MainPresenter(this)
        presenter?.onActivityCreated()
    }

    override fun setupRecyclerView(selector: CallSelector, adapter: MovieAdapter) {
        when (selector){
            CallSelector.MOST_POPULAR -> {
                mostPopularRv?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                mostPopularRv?.adapter = adapter
            }

            CallSelector.TOP_RATED -> {
                topMovieRv?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                mostPopularRv?.adapter = adapter
            }

            CallSelector.NOW_PLAYING -> {
                nowPlayingRv?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                nowPlayingRv?.adapter = adapter
            }
        }
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
}