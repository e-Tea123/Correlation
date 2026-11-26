package com.correlation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.correlation.ui.navigation.specs.IScreenSpec

@Composable
fun CorrelationTopBar(navController: NavHostController){
    val navBackStackEntryState = navController.currentBackStackEntryAsState()
    IScreenSpec.TopBar(navBackStackEntryState.value, navController)
}

@Preview
@Composable
fun PreviewCorrelationTopBar(){
    CorrelationTopBar(navController = rememberNavController())
}