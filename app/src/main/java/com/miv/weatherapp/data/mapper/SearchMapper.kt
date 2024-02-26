package com.miv.weatherapp.data.mapper

import com.miv.weatherapp.data.network.dto.CityDto
import com.miv.weatherapp.domain.entity.City

fun CityDto.toEntity() = City(id, name, country)
fun List<CityDto>.toEntities() = map { it.toEntity() }