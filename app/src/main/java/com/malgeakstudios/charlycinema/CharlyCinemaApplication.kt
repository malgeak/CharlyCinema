package com.malgeakstudios.charlycinema

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CharlyCinemaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}