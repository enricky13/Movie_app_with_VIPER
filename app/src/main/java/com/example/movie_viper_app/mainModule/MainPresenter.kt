package com.example.movie_viper_app.mainModule

class MainPresenter(private var view: MainContract.View?) : MainContract.Presenter{
    private var interactor : MainContract.Interactor = MainInteractor()
    private var router : MainContract.Router = MainRouter()

    override fun onActivityCreated() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        view = null
    }

}