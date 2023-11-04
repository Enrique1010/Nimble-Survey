package com.erapp.nimblesurvey.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.erapp.nimblesurvey.navigation.NimbleSurveyNavHost
import com.erapp.nimblesurvey.ui.theme.NimbleSurveyTheme
import com.erapp.nimblesurvey.utils.NavigationRoutes.AUTH_ROUTE
import com.erapp.nimblesurvey.utils.NavigationRoutes.HOME_ROUTE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Load the startDestination route before displaying any content, so we can navigate directly to the
        // appropriate screen without flashing the UI at all.
        val startDestination = if (viewModel.isAuthenticated) {
            HOME_ROUTE
        } else {
            AUTH_ROUTE
        }

        setContent {
            NimbleSurveyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NimbleSurveyNavHost(startDestination = startDestination)
                }
            }
        }
    }
}