package com.example.movie_viper_app.detailModule

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.movie_viper_app.R
import com.example.movie_viper_app.databinding.ActivityDetailBinding
import com.example.movie_viper_app.entity.Results
import com.example.movie_viper_app.enums.MovieBase

class DetailActivity : AppCompatActivity(), DetailContract.View {
    private var presenter: DetailPresenter? = null
    private var detailBinding : ActivityDetailBinding? = null
    private var glide: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        glide = Glide.with(this)
        presenter = DetailPresenter(this)
        presenter?.onActivityCreated()
        presenter?.getIntentData(intent.getSerializableExtra(MovieBase.INTENT_HOLDER) as Results)


        detailBinding?.bnTrailerDetailActivity?.setOnClickListener { presenter?.trailerButtonClicked() }
    }

    override fun displayMovieImage(image: String) {
        detailBinding?.ivMovieImageDetailActivity?.let { iv ->
            glide?.load(MovieBase.MEDIUM_SIZE + image)
                ?.into(iv)
        }
    }

    override fun displayMovieName(title: String) {
        detailBinding?.tvMovieNameDetailActivity?.text = title
    }

    override fun displayMovieRating(rating: Float) {
        detailBinding?.rbMovieRatingDetailActivity?.rating = rating / 2
    }

    override fun displayMovieDescription(description: String) {
        detailBinding?.tvMovieDescriptionDetailActivity?.text = description
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
