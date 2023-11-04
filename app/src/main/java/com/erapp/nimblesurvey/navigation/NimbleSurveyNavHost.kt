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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.erapp.nimblesurvey.utils.NavigationRoutes.AUTH_ROUTE
import com.erapp.nimblesurvey.utils.NavigationRoutes.HOME_ROUTE
import com.erapp.nimblesurvey.utils.popUpToTop

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NimbleSurveyNavHost(
    startDestination: String,
) {
    val navController = rememberNavController()
    var isVisible by rememberSaveable { mutableStateOf(false) } // logout dialog if needed

    // perform clean navigation to auth route when user logout
    fun navigateToRoute(route: String) {
        navController.navigate(route) {
            popUpToTop(navController)
            launchSingleTop = true
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