package com.miv.weatherapp.data.network.dto

import com.google.gson.annotations.SerializedName
import com.miv.weatherapp.domain.entity.Forecast

data class WeatherForecastDto(
    @SerializedName("current") val current: WeatherDto,
    @SerializedName("forecast") val forecastDto: ForecastDto
)
