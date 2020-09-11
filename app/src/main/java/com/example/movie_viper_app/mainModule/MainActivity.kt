package com.example.movie_viper_app.mainModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_viper_app.R
import com.example.movie_viper_app.adapter.MovieAdapter
import com.example.movie_viper_app.entity.CallSelector
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainContract.View{
    private var presenter : MainPresenter? = null
    private var linearLayoutManager : LinearLayoutManager? = null
    private val topMovieRv : RecyclerView? by lazy { recyclerview_top_movies }
    private val nowPlayingRv : RecyclerView? by lazy { recyclerview_now_playing_movies }
    private val mostPopularRv : RecyclerView? by lazy { recyclerview_popular_movies }
    private val topMoviePb : ProgressBar? by lazy { top_movie_pb }
    private val nowPlayingPb : ProgressBar? by lazy { now_playing_pb }
    private val mostPopularPb : ProgressBar? by lazy { popular_movies_pb }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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