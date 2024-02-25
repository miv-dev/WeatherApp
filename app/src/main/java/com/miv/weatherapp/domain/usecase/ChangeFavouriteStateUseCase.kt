package com.miv.weatherapp.domain.usecase

import com.miv.weatherapp.domain.entity.City
import com.miv.weatherapp.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangeFavouriteStateUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {

    suspend fun addToFavourite(city: City) = repository.addToFavourite(city)
    suspend fun removeFromFavourite(cityId: Int) = repository.removeFromFavourite(cityId)

}