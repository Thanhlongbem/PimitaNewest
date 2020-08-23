package com.bacl.pimita

import android.app.Application

class App : Application() {
    companion object {
        private lateinit var instance: App
        fun shared(): App {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}