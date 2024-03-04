package com.miv.weatherapp.presentation.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.miv.weatherapp.domain.entity.City
import com.miv.weatherapp.presentation.details.DefaultDetailsComponent
import com.miv.weatherapp.presentation.details.DefaultDetailsComponent_Factory
import com.miv.weatherapp.presentation.favourite.DefaultFavouriteComponent
import com.miv.weatherapp.presentation.search.DefaultSearchComponent
import com.miv.weatherapp.presentation.search.OpenReason
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize

class DefaultRootComponent @AssistedInject constructor(
    private val detailsComponentFactory: DefaultDetailsComponent.Factory,
    private val favouriteComponentFactory: DefaultFavouriteComponent.Factory,
    private val searchComponentFactory: DefaultSearchComponent.Factory,
    @Assisted("componentContext") componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>>
        get() = childStack(
            source = navigation,
            initialConfiguration = Config.Favorite,
            handleBackButton = true,
            childFactory = ::child,
        )

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child {
        return when (config) {
            is Config.Favorite -> {
                val component = favouriteComponentFactory.create(
                    onCityItemClicked = { city -> navigation.push(Config.Details(city)) },
                    onSearchClicked = { navigation.push(Config.Search(OpenReason.RegularSearch)) },
                    onAddFavouriteClicked = { navigation.push(Config.Search(OpenReason.AddToFavourite)) },
                    componentContext = componentContext
                )
                RootComponent.Child.Favourite(component)
            }

            is Config.Search -> {
                val component = searchComponentFactory.create(
                    openReason = config.openReason,
                    onClickBack = { navigation.pop() },
                    onCitySavedToFavourite = { navigation.pop() },
                    onForecastForCityRequested = { city -> navigation.push(Config.Details(city)) },
                    componentContext = componentContext
                )
                RootComponent.Child.Search(component)

            }

            is Config.Details -> {
                val component = detailsComponentFactory.create(
                    city = config.city,
                    onBackClicked = { navigation.pop() },
                    componentContext = componentContext
                )
                RootComponent.Child.Details(component)

            }
        }
    }

    sealed interface Config : Parcelable {
        @Parcelize
        data object Favorite : Config

        @Parcelize
        data class Search(val openReason: OpenReason) : Config

        @Parcelize
        data class Details(val city: City) : Config
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultRootComponent
    }
}