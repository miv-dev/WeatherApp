package com.miv.weatherapp.data.repository

import com.miv.weatherapp.data.local.db.FavouriteCitiesDao
import com.miv.weatherapp.data.mapper.toDbModel
import com.miv.weatherapp.data.mapper.toEntities
import com.miv.weatherapp.domain.entity.City
import com.miv.weatherapp.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteCitiesDao: FavouriteCitiesDao
) : FavouriteRepository {
    override val favouriteCities: Flow<List<City>>
        get() = favouriteCitiesDao.getFavouriteCities()
            .map { it.toEntities() }

    override fun observeIsFavourite(cityId: Int): Flow<Boolean> =
        favouriteCitiesDao.observeIsFavourite(cityId)

    override suspend fun addToFavourite(city: City) {
        favouriteCitiesDao.addToFavourite(city.toDbModel())
    }

    override suspend fun removeFromFavourite(cityId: Int) {
        favouriteCitiesDao.removeFromFavourite(cityId)
    }
}