package com.miv.weatherapp.presentation.favourite

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.miv.weatherapp.domain.entity.City
import com.miv.weatherapp.presentation.extensions.componentScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultFavouriteComponent @AssistedInject constructor(
    private val factory: FavouriteStoreFactory,
    @Assisted("onCityItemClicked") private val onCityItemClicked: (City) -> Unit,
    @Assisted("onSearchClicked") private val onSearchClicked: () -> Unit,
    @Assisted("onAddFavouriteClicked") private val onAddFavouriteClicked: () -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : FavouriteComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { factory.create() }
    private val scope = componentScope()

    init {

        scope.launch {
            store.labels.collect {
                when (it) {
                    is FavouriteStore.Label.CityItemClick -> onCityItemClicked(it.city)
                    FavouriteStore.Label.ClickSearch -> onClickSearch()
                    FavouriteStore.Label.ClickToFavourite -> onClickAddFavourite()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<FavouriteStore.State>
        get() = store.stateFlow

    override fun onClickSearch() {
        store.accept(FavouriteStore.Intent.ClickSearch)
    }

    override fun onClickAddFavourite() {
        store.accept(FavouriteStore.Intent.ClickAddToFavourite)
    }

    override fun onCityItemClick(city: City) {
        store.accept(FavouriteStore.Intent.CityItemClick(city))
    }


    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("onCityItemClicked") onCityItemClicked: (City) -> Unit,
            @Assisted("onSearchClicked") onSearchClicked: () -> Unit,
            @Assisted("onAddFavouriteClicked") onAddFavouriteClicked: () -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultFavouriteComponent
    }

}