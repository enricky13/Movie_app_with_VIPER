package com.example.movie_viper_app.mainModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
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
    private var topMoviePb : ProgressBar? = null
    private var nowPlayingPb : ProgressBar? = null
    private var mostPopularPb : ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        topMovieRv = findViewById(R.id.recyclerview_top_movies)
        nowPlayingRv = findViewById(R.id.recyclerview_now_playing_movies)
        mostPopularRv = findViewById(R.id.recyclerview_popular_movies)
        topMoviePb = findViewById(R.id.top_movie_pb)
        nowPlayingPb = findViewById(R.id.now_playing_pb)
        mostPopularPb = findViewById(R.id.popular_movies_rb)

        linearLayoutManager = LinearLayoutManager(this)
        mostPopularRv?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        topMovieRv?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        nowPlayingRv?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        presenter = MainPresenter(this)
        presenter?.onActivityCreated()
    }

    override fun setupRecyclerView(selector: CallSelector, adapter: MovieAdapter) {
        when (selector){
            CallSelector.MOST_POPULAR -> {
                mostPopularRv?.adapter = adapter
                mostPopularPb?.visibility = ProgressBar.GONE
            }

            CallSelector.TOP_RATED -> {
                topMovieRv?.adapter = adapter
                topMoviePb?.visibility = ProgressBar.GONE
            }

            CallSelector.NOW_PLAYING -> {
                nowPlayingRv?.adapter = adapter
                nowPlayingPb?.visibility = ProgressBar.GONE
            }
        }
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
}