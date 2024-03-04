package com.miv.weatherapp.presentation.search

import com.miv.weatherapp.domain.entity.City
import kotlinx.coroutines.flow.StateFlow
import retrofit2.http.Query

interface SearchComponent {
    val model: StateFlow<SearchStore.State>


    fun changeSearchQuery(query: String)

    fun onClickBack()

    fun onClickSearch()

    fun onClickCity(city: City)

}