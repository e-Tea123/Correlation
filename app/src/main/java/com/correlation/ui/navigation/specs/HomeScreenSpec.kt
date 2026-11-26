package com.correlation.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.correlation.R

object HomeScreenSpec: IScreenSpec {
    override val route = "home"

    override val title = R.string.home_screen_title

    @Composable
    override fun Content(
        modifier: Modifier,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry
    ) {
        TODO("Not yet implemented")
    }

    @Composable
    override fun TopAppBarActions(
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?
    ) {
        TODO("Not yet implemented")
    }
}


@Preview
@Composable
fun PreviewHomeScreen(){

}

