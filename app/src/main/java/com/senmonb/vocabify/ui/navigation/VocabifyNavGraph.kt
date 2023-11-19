package com.senmonb.vocabify.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.senmonb.vocabify.ui.screen.HomeDestination
import com.senmonb.vocabify.ui.screen.HomeScreen

@Composable
fun VocabifyNavHost(
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry?,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = HomeDestination.route, modifier = modifier) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                modifier = modifier,
            )
        }
    }
}
