package com.example.movie_viper_app.mainModule

interface MainContract {
    interface View{

    }
    interface Interactor{

    }
    interface Presenter{
        fun onActivityCreated()
        fun onDestroy()
    }
    interface Router{

    }
}