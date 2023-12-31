package com.bhaskarblur.dictionaryapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}