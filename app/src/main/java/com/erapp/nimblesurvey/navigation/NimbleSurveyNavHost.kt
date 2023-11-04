package com.erapp.nimblesurvey.navigation

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.erapp.nimblesurvey.ui.MainViewModel
import com.erapp.nimblesurvey.utils.NavigationRoutes.AUTH_ROUTE
import com.erapp.nimblesurvey.utils.popUpToTop

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NimbleSurveyNavHost(
    startDestination: String,
    authState: MainViewModel.MainActivityState
) {
    val navController = rememberNavController()

    // perform navigation based on the authState
    LaunchedEffect(key1 = authState) {
        if (authState is MainViewModel.MainActivityState.Unauthorized) {
            navController.navigate(AUTH_ROUTE) {
                popUpToTop(navController)
                launchSingleTop = true
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                ),
            navController = navController,
            startDestination = startDestination
        ) {
            authNavGraph(navController)
            homeNavGraph(navController)
        }
    }
}