package com.correlation.ui.navigation.specs

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.correlation.R
import com.correlation.ui.home.HomeScreen
import com.correlation.ui.viewmodel.CalendarViewModel
import com.correlation.ui.viewmodel.CorrelationViewModelFactory
import com.correlation.ui.viewmodel.collectInLaunchedEffect
import com.correlation.ui.viewmodel.intent.CalendarIntent

object HomeScreenSpec: IScreenSpec {
    override val route = "home"

    override val title = R.string.home_screen_title

    @Composable
    override fun Content(
        modifier: Modifier,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry
    ) {
        val context = LocalContext.current

        val viewModel = ViewModelProvider(
            store = navBackStackEntry.viewModelStore,
            factory = CorrelationViewModelFactory(),
            defaultCreationExtras = CorrelationViewModelFactory.creationExtras(
                defaultCreationExtras = navBackStackEntry.defaultViewModelCreationExtras,
                context = context
            )
        )[CalendarViewModel::class]

        val (state, dispatcher, effects) = viewModel.use(navBackStackEntry)

        viewModel.effectFlow.collectInLaunchedEffect {
        }

        HomeScreen(
            onDayClick = {day, dayList -> dispatcher.invoke(CalendarIntent.SelectDay(day))}
        )
    }

    @Composable
    override fun TopAppBarActions(
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?
    ) {}
}

