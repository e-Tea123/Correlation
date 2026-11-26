package com.correlation.ui.navigation.specs

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.correlation.R

sealed interface IScreenSpec {
    companion object {
        private const val LOG_TAG = "Correlation.IScreenSpec"

        val allScreens = IScreenSpec::class.sealedSubclasses.associate {
            Log.d(LOG_TAG, "allScreens: mapping route \"${it.objectInstance?.route ?: ""}\" to object \"${it.objectInstance}\"")
            it.objectInstance?.route to it.objectInstance
        }
        const val ROOT = "samodelkin"
        val startDestination = HomeScreenSpec.route

        @Composable
        fun TopBar(navBackStackEntry: NavBackStackEntry?, navHost: NavHostController){
            val route = navBackStackEntry?.destination?.route ?: ""
            allScreens[route]?.TopAppBarContent(navHost, navBackStackEntry)
        }
    }

    val route: String
    val title: Int

    @Composable
    fun Content(
        modifier: Modifier,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TopAppBarContent(navHost: NavHostController, navBackStackEntry: NavBackStackEntry?){
        TopAppBar(
            navigationIcon = if(navHost.previousBackStackEntry != null) {
            {
                IconButton(onClick = {navHost.navigateUp()}){
                    Image(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = stringResource(id = R.string.menu_back_desc)
                    )
                }
            }
            } else {
                {}
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue),
            title = {Text(stringResource(title), color = Color.White)},
            actions = {TopAppBarActions(navHost, navBackStackEntry)}
        )
    }

    @Composable
    abstract fun TopAppBarActions(navController: NavHostController, navBackStackEntry: NavBackStackEntry?)
}
