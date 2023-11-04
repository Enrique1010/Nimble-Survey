package com.erapp.nimblesurvey.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.erapp.nimblesurvey.ui.screens.details.SurveyDetailsScreen
import com.erapp.nimblesurvey.ui.screens.home.HomeScreen
import com.erapp.nimblesurvey.ui.screens.home.HomeScreenViewModel
import com.erapp.nimblesurvey.utils.NavigationRoutes.HOME_ROUTE
import com.erapp.nimblesurvey.utils.composable
import com.erapp.nimblesurvey.utils.safeNavigate

fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
    onLogout: () -> Unit = {}
) {
    navigation(
        startDestination = NavigationHelper.HomeScreen.route,
        route = HOME_ROUTE
    ) {
        composable(NavigationHelper.HomeScreen) {
            val viewModel = hiltViewModel<HomeScreenViewModel>()

            HomeScreen(
                homeScreenData = viewModel.homeScreenData,
                homeScreenState = viewModel.homeScreenState,
                onEvent = viewModel::onEvent,
                onLogout = onLogout,
                onSurveyButtonPressed = {
                    navController.safeNavigate(NavigationHelper.SurveyDetailsScreen.route)
                }
            )
        }

        composable(NavigationHelper.SurveyDetailsScreen) {
            SurveyDetailsScreen {
                navController.popBackStack()
            }
        }
    }
}