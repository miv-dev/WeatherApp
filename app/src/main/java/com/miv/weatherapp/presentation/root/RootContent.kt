package com.miv.weatherapp.presentation.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.miv.weatherapp.presentation.details.DetailsContent
import com.miv.weatherapp.presentation.favourite.FavouriteContent
import com.miv.weatherapp.presentation.search.SearchContent
import com.miv.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
fun RootContent(component: RootComponent) {
    WeatherAppTheme {
        Children(stack = component.stack) { child ->
            when (val instance = child.instance) {
                is RootComponent.Child.Favourite -> FavouriteContent(instance.component)
                is RootComponent.Child.Search -> SearchContent(instance.component)
                is RootComponent.Child.Details -> DetailsContent(instance.component)

            }

        }
    }
}