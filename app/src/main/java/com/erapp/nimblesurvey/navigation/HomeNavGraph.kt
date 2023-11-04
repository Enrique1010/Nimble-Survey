package com.erapp.nimblesurvey.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.erapp.nimblesurvey.ui.screens.home.HomeScreen
import com.erapp.nimblesurvey.ui.screens.home.HomeScreenViewModel
import com.erapp.nimblesurvey.utils.NavigationRoutes.HOME_ROUTE
import com.erapp.nimblesurvey.utils.composable

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation(
        startDestination = NavigationHelper.HomeScreen.route,
        route = HOME_ROUTE
    ) {
        composable(NavigationHelper.HomeScreen) {
            val viewModel = hiltViewModel<HomeScreenViewModel>()

            HomeScreen(
                surveys = viewModel.homeScreenData.surveys,
                homeScreenState = viewModel.homeScreenState,
                onEvent = viewModel::onEvent,
            )
        }
    }
}