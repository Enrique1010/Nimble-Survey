package com.erapp.nimblesurvey.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.erapp.nimblesurvey.ui.screens.auth.ForgotPasswordScreen
import com.erapp.nimblesurvey.ui.screens.auth.ForgotPasswordViewModel
import com.erapp.nimblesurvey.ui.screens.auth.LoginScreen
import com.erapp.nimblesurvey.ui.screens.auth.LoginScreenViewModel
import com.erapp.nimblesurvey.utils.NavigationRoutes.AUTH_ROUTE
import com.erapp.nimblesurvey.utils.composable
import com.erapp.nimblesurvey.utils.popUpToTop
import com.erapp.nimblesurvey.utils.safeNavigate

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        startDestination = NavigationHelper.LoginScreen.route,
        route = AUTH_ROUTE
    ) {
        composable(NavigationHelper.LoginScreen) {
            val viewModel = hiltViewModel<LoginScreenViewModel>()

            LoginScreen(
                loginFields = viewModel.loginFields,
                isLoginEnabled = viewModel.enableLogin,
                isValidEmail = viewModel.validEmail,
                loginState = viewModel.loginState,
                onEvent = viewModel::onEvent,
                goToForgotPassword = {
                    navController.safeNavigate(NavigationHelper.ForgotPasswordScreen.route)
                },
                goToHomeScreen = {
                    navController.navigate(NavigationHelper.HomeScreen.route) {
                        popUpToTop(navController)
                    }
                }
            )
        }
        composable(NavigationHelper.ForgotPasswordScreen) {
            val viewModel = hiltViewModel<ForgotPasswordViewModel>()

            ForgotPasswordScreen(
                forgotPasswordInfo = viewModel.forgotPasswordInfo,
                forgotPasswordState = viewModel.forgotPasswordState,
                isForgotPasswordInfoValid = viewModel.isForgotPasswordEnabled,
                isValidEmail = viewModel.validEmail,
                onEvent = viewModel::onEvent,
                goToLoginScreen = {
                    navController.navigate(NavigationHelper.LoginScreen.route) {
                        popUpToTop(navController)
                    }
                },
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}