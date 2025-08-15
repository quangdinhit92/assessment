package com.dinh.feature.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument


const val DETAIL_ID = "id"

sealed class AppDestination(val route: String) {
    open var destination: String = route
    open val arguments: List<NamedNavArgument> = emptyList()

    object HomeScreen : AppDestination("home")
    object DetailScreen : AppDestination("detail/{$DETAIL_ID}") {
        override val arguments: List<NamedNavArgument> =
            listOf(navArgument(DETAIL_ID) { type = NavType.StringType })

        fun buildDestination(id: Long) = apply {
            destination = "detail/$id"
        }
    }
    object VisitGarden: AppDestination(route = "visitgarden")
    object SelectCrop: AppDestination(route = "selectcrop")
    object LoginScreen: AppDestination(route = "login")
    object MyGardenScreen: AppDestination(route = "mygarden")
}