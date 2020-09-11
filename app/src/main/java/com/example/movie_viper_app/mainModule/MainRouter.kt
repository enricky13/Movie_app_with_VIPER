package com.example.movie_viper_app.mainModule

import android.app.Activity
import android.content.Intent
import com.example.movie_viper_app.detailModule.DetailActivity
import com.example.movie_viper_app.entity.Results
import java.io.Serializable

class MainRouter(var activity: Activity?) : MainContract.Router {

    override fun goToDetailActivity(results: Results) {
        Intent(activity, DetailActivity::class.java).apply {
            putExtra("FINDME", results as Serializable)
            activity?.startActivity(this)
        }
    }

}