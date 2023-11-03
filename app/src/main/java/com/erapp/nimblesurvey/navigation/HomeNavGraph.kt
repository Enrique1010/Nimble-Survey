package com.erapp.nimblesurvey.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.erapp.nimblesurvey.ui.screens.home.HomeScreen
import com.erapp.nimblesurvey.utils.NavigationRoutes.HOME_ROUTE
import com.erapp.nimblesurvey.utils.composable

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation(
        startDestination = NavigationHelper.HomeScreen.route,
        route = HOME_ROUTE
    ) {
        composable(NavigationHelper.HomeScreen) {
            HomeScreen()
        }
    }
}