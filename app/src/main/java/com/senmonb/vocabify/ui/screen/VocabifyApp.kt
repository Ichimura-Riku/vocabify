package com.senmonb.vocabify.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.senmonb.vocabify.ui.navigation.VocabifyNavHost

@Composable
fun VocabifyApp(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    VocabifyNavHost(navController = navController, navBackStackEntry = navBackStackEntry)
}
