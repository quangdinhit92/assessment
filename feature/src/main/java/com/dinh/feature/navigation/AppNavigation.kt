package com.dinh.feature.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dinh.feature.ui.screen.detail.DetailScreen
import com.dinh.feature.ui.screen.home.HomeScreen
import com.dinh.feature.ui.screen.selectcrop.SelectCropScreen
import com.dinh.feature.ui.screen.visitgardern.VisitGardenScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestination.VisitGarden.destination
) {
    NavHost(
        modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()),
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppDestination.HomeScreen.route)
        {
            HomeScreen(onNavigate = {
                navController.navigate(it.destination)
            })
        }
        composable(AppDestination.DetailScreen.route)
        {
            val argument = it.arguments
            argument?.let {
                val myId = it.getString(DETAIL_ID, "")
                DetailScreen(
                    id = myId,
                    onNavigate = { navController.navigate(it.destination) })
            }
        }
        composable(route = AppDestination.VisitGarden.route)
        {
            VisitGardenScreen(onNavigate = {
                navController.navigate(it.destination)
            })
        }
        composable(route = AppDestination.SelectCrop.route)
        {
            SelectCropScreen()
        }


    }
}