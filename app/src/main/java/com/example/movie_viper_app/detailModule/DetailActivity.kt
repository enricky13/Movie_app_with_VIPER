package com.example.movie_viper_app.detailModule

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.movie_viper_app.R
import com.example.movie_viper_app.entity.Results
import com.example.movie_viper_app.enums.MovieBase
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailContract.View {
    private var presenter: DetailPresenter? = null
    private val movieImageIv: ImageView? by lazy { iv_movie_image_detailActivity }
    private val movieNameTv: TextView? by lazy { tv_movie_name_detailActivity }
    private val movieRatingRb: RatingBar? by lazy { rb_movie_rating_detailActivity }
    private val movieDescriptionTv: TextView? by lazy { tv_movie_description_detailActivity }
    private val trailerButton: Button? by lazy { bn_trailer_detailActivity }
    private var glide: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        glide = Glide.with(this)
        presenter = DetailPresenter(this)
        presenter?.onActivityCreated()
        presenter?.getIntentData(intent.getSerializableExtra(MovieBase.INTENT_HOLDER) as Results)

        trailerButton?.setOnClickListener { presenter?.trailerButtonClicked() }
    }

    override fun displayMovieImage(image: String) {
        movieImageIv?.let { iv ->
            glide?.load(MovieBase.MEDIUM_SIZE + image)
                ?.into(iv)
        }
    }

    override fun displayMovieName(title: String) {
        movieNameTv?.text = title
    }

    override fun displayMovieRating(rating: Float) {
        movieRatingRb?.rating = rating / 2
    }

    override fun displayMovieDescription(description: String) {
        movieDescriptionTv?.text = description
    }

    override fun displayMovieTrailer(youtubeLink: String) {
        Intent(Intent.ACTION_VIEW, Uri.parse(MovieBase.YOUTUBE_BASE+youtubeLink)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            setPackage(YOUTUBE_PACKAGE)
            startActivity(this)
        }
    }

    companion object {
        const val YOUTUBE_PACKAGE = "com.google.android.youtube"
    }
}