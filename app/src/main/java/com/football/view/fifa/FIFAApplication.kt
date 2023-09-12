package com.football.view.fifa

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FIFAApplication : Application() {
    companion object {
        lateinit var instance: FIFAApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}