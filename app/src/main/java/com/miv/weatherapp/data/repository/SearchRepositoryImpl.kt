package com.miv.weatherapp.data.repository

import com.miv.weatherapp.data.mapper.toEntities
import com.miv.weatherapp.data.network.api.ApiService
import com.miv.weatherapp.domain.entity.City
import com.miv.weatherapp.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SearchRepository {
    override suspend fun search(query: String): List<City> {
        return apiService.searchCity(query).toEntities()
    }
}