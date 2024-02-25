package com.miv.weatherapp.data.network.dto

import com.google.gson.annotations.SerializedName
import com.miv.weatherapp.domain.entity.Forecast

data class ForecastDto(
    @SerializedName("forecastday") val forecastDay: List<DayDto>
)
