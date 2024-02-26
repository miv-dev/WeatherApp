package com.miv.weatherapp

import android.app.Application
import com.miv.weatherapp.di.ApplicationComponent
import com.miv.weatherapp.di.DaggerApplicationComponent

class WeatherApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
            .factory().create(this)
    }

}