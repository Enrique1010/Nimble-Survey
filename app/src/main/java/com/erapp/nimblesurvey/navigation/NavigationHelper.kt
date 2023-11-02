package com.erapp.nimblesurvey.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 *
 * Routes helper class to handle navigation routes, destinations and arguments
 *
 * @property baseRoute the base route of the destination
 * @property route the full route of the destination
 * @property args the list of arguments for the destination
 *
 * */
sealed class NavigationHelper(
    val baseRoute: String,
    private val navArgs: List<NavArgs> = emptyList(),
) {
    val route = run {
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute).plus(argKeys).joinToString("/")
    }

    val args = navArgs.map {
        navArgument(name = it.key) {
            this.type = it.navType
            this.defaultValue = it.defaultValue
            this.nullable = it.nullable
        }
    }

    //** here you can add your destination objects **//
}

enum class NavArgs(
    val key: String,
    val navType: NavType<*>,
    val nullable: Boolean = false,
    val defaultValue: Any? = null,
) {
    //** here you can add your navArgs with the key, type and default value **//
}
