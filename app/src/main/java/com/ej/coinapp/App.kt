package com.ej.coinapp

import android.app.Application
import android.content.Context
import timber.log.Timber

class App: Application() {
    init {
        instance = this
    }
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
    companion object{
        private var instance : App? = null
        fun context() : Context {
            return instance!!.applicationContext
        }
    }
}