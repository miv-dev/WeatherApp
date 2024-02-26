package com.miv.weatherapp.data.mapper

import com.miv.weatherapp.data.local.model.CityDbModel
import com.miv.weatherapp.domain.entity.City

fun City.toDbModel() = CityDbModel(id, name, country)

fun CityDbModel.toEntity() = City(id, name, country)

fun List<CityDbModel>.toEntities() = map { it.toEntity() }