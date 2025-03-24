package com.dinh.feature.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dinh.feature.ui.screen.detail.DetailScreen
import com.dinh.feature.ui.screen.home.HomeScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestination.HomeScreen.destination
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(AppDestination.HomeScreen.route)
        {
            HomeScreen(onNavigate = {
                navController.navigate(it.destination) })
        }
        composable(AppDestination.DetailScreen.route)
        {
            val argument =it.arguments
            argument?.let {
              val myId=  it.getString(DETAIL_ID, "")
                DetailScreen(
                    id = myId,
                    onNavigate = { navController.navigate(it.destination) })
            }


        }

    }
}