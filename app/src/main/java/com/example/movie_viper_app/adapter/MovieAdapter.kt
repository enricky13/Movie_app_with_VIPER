package com.example.movie_viper_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.movie_viper_app.R
import com.example.movie_viper_app.entity.Results
import com.example.movie_viper_app.enums.MovieBase
import com.example.movie_viper_app.mainModule.MainContract

class MovieAdapter(
    private val result: List<Results>,
    private var movieClickListener : MainContract.Presenter.MovieClickListener?
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent, movieClickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(result[position])
    }

    override fun getItemCount(): Int {
        return result.size
    }

    class MovieViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        private val listener: MainContract.Presenter.MovieClickListener?
    ) : RecyclerView.ViewHolder(inflater.inflate(R.layout.movie_list_itemview, parent, false)) {
        private var moviePoster: ImageView? = null
        private var movieName: TextView? = null
        private var movieRating: RatingBar? = null
        private var glide: RequestManager? = null

        init {
            moviePoster = itemView.findViewById(R.id.iv_movie_image_itemview)
            movieName = itemView.findViewById(R.id.tv_movie_name_itemview)
            movieRating = itemView.findViewById(R.id.rb_movie_rating_itemview)
            glide = Glide.with(parent.context)
        }

        fun onBind(result: Results) {
            movieName?.text = result.title
            movieRating?.rating = result.voteAverage.toFloat() / 2
            moviePoster?.let {
                glide?.load(MovieBase.MEDIUM_SIZE + result.image)
                    ?.into(it)
            }

            movieRating?.isFocusable = false

            itemView.setOnClickListener { listener?.onMovieClick(result) }
        }
    }
}
