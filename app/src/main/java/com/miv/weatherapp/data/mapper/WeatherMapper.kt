package com.miv.weatherapp.data.mapper

import com.miv.weatherapp.data.network.dto.DayDto
import com.miv.weatherapp.data.network.dto.ForecastDto
import com.miv.weatherapp.data.network.dto.WeatherCurrentDto
import com.miv.weatherapp.data.network.dto.WeatherDto
import com.miv.weatherapp.data.network.dto.WeatherForecastDto
import com.miv.weatherapp.domain.entity.Forecast
import com.miv.weatherapp.domain.entity.Weather
import java.util.Calendar
import java.util.Date

fun WeatherCurrentDto.toEntity() = current.toEntity()

fun WeatherDto.toEntity() =
    Weather(
        tempC = tempC,
        conditionText = conditionDto.text,
        conditionUrl = conditionDto.iconUrl.correctImageUrl(),
        date = date.toCalendar()
    )

fun WeatherForecastDto.toEntity() = Forecast(current.toEntity(), forecastDto.toEntity())

fun ForecastDto.toEntity() = forecastDay.drop(1).map { it.toEntity() }

fun DayDto.toEntity() = Weather(
    tempC = dayWeatherDto.tempC,
    conditionText = dayWeatherDto.conditionDto.text,
    conditionUrl = dayWeatherDto.conditionDto.iconUrl.correctImageUrl(),
    date = date.toCalendar(),
)

private fun Long.toCalendar() = Calendar.getInstance().apply {
    time = Date(this@toCalendar * 1000)
}

private fun String.correctImageUrl() = "https:$this".replace("64x64", "128x128")