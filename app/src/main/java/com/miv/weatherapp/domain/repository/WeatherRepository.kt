package com.miv.weatherapp.domain.repository

import com.miv.weatherapp.domain.entity.City
import com.miv.weatherapp.domain.entity.Forecast
import com.miv.weatherapp.domain.entity.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeather(cityId: Int): Weather

    suspend fun getForecast(cityId: Int): Forecast


}