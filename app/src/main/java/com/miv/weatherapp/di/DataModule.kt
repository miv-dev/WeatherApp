package com.miv.weatherapp.di

import android.content.Context
import com.miv.weatherapp.data.local.db.FavouriteCitiesDao
import com.miv.weatherapp.data.local.db.FavouriteDatabase
import com.miv.weatherapp.data.network.api.ApiFactory
import com.miv.weatherapp.data.network.api.ApiService
import com.miv.weatherapp.data.repository.FavouriteRepositoryImpl
import com.miv.weatherapp.data.repository.SearchRepositoryImpl
import com.miv.weatherapp.data.repository.WeatherRepositoryImpl
import com.miv.weatherapp.domain.repository.FavouriteRepository
import com.miv.weatherapp.domain.repository.SearchRepository
import com.miv.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @[ApplicationScope Binds]
    fun bindFavouriteRepository(impl: FavouriteRepositoryImpl): FavouriteRepository

    @[ApplicationScope Binds]

    fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

    @[ApplicationScope Binds]

    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository


    companion object {

        @[ApplicationScope Provides]
        fun provideApiService(): ApiService = ApiFactory.apiService

        @[ApplicationScope Provides]
        fun provideFavoriteDatabase(context: Context): FavouriteDatabase {
            return FavouriteDatabase.getInstance(context)
        }

        @[ApplicationScope Provides]
        fun provideFavouriteCitiesDao(database: FavouriteDatabase): FavouriteCitiesDao =
            database.favouriteCitiesDao()
    }

}