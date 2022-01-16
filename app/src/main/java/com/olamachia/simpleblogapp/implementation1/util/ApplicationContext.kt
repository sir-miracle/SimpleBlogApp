package com.olamachia.simpleblogapp.implementation1.util

import android.app.Application

class Application: Application() {
    var networkManager = NetworkManager()

    fun checkNetwork() = networkManager.hasInternetConnection(this)


}

