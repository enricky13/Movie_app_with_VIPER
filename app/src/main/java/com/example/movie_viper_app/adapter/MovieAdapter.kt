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
import com.example.movie_viper_app.entity.CallSelector
import com.example.movie_viper_app.entity.Results

class MovieAdapter(
    private val result: List<Results>,
    private val selector: CallSelector
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(result[position])
    }

    override fun getItemCount(): Int {
        return result.size
    }

    class MovieViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(inflater.inflate(R.layout.movie_list_itemview, parent, false)) {
        private var moviePoster: ImageView? = null
        private var movieName: TextView? = null
        private var movieRating: RatingBar? = null
        private var glide : RequestManager? = null

        init {
            moviePoster = itemView.findViewById(R.id.iv_movie_image_itemview)
            movieName = itemView.findViewById(R.id.tv_movie_name_itemview)
            movieRating = itemView.findViewById(R.id.rb_movie_rating_itemview)
            glide = Glide.with(parent.context)
        }

        fun onBind(result: Results) {
            movieName?.text = result.title
            movieRating?.rating = result.popularity.toFloat()
            moviePoster?.let {
                glide?.load(result.image ?: "")
                    ?.into(it)
            }
        }
    }
}
