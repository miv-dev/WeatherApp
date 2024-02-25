package com.miv.weatherapp.domain.repository

import com.miv.weatherapp.domain.entity.City

interface SearchRepository {
    suspend fun search(query: String): List<City>

}