package com.miv.weatherapp.presentation

import android.os.Bundle
import android.provider.DocumentsContract.Root
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.defaultComponentContext
import com.miv.weatherapp.WeatherApp
import com.miv.weatherapp.data.network.api.ApiFactory
import com.miv.weatherapp.presentation.root.DefaultRootComponent
import com.miv.weatherapp.presentation.root.RootContent
import com.miv.weatherapp.presentation.ui.theme.WeatherAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var rootComponentFactory: DefaultRootComponent.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as WeatherApp).applicationComponent.inject(this)

        super.onCreate(savedInstanceState)


        setContent {
            RootContent(component = rootComponentFactory.create(defaultComponentContext()))
        }
    }
}