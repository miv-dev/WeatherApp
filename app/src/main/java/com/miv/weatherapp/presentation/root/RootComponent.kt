package com.miv.weatherapp.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.miv.weatherapp.presentation.details.DetailsComponent
import com.miv.weatherapp.presentation.favourite.FavouriteComponent
import com.miv.weatherapp.presentation.search.SearchComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>


    sealed interface Child {
        data class Favourite(val component: FavouriteComponent) : Child
        data class Search(val component: SearchComponent) : Child
        data class Details(val component: DetailsComponent) : Child


    }

}