package com.dinh.feature.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dinh.feature.R
import com.dinh.feature.navigation.AppDestination
import com.dinh.feature.ui.component.BottomNavigationBar
import com.dinh.feature.ui.component.NavItem

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    onNavigate: (AppDestination) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    val navItems = listOf(
        NavItem(1, "Home", R.drawable.ic_home),
        NavItem(2, "Plots", R.drawable.ic_plots),
        NavItem(3, "Tasks", R.drawable.ic_tasks),
        NavItem(4, "Earnings", R.drawable.ic_earnings),
        NavItem(5, "Profile", R.drawable.ic_profile),
    )

    uiState.errorMessage?.let { error ->
        LaunchedEffect(error) {
            viewModel.clearError()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background image that fills the entire screen
        Image(
            painter = painterResource(id = R.drawable.bg_main),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Scaffold with transparent background
        Scaffold(
            backgroundColor = Color.Transparent,
            bottomBar = {
                BottomNavigationBar(
                    items = navItems,
                    selectedId = uiState.selectedNavId,
                    onItemClick = { clickedItem ->
                        viewModel.selectNavigationItem(clickedItem.id)
                    }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (uiState.selectedNavId) {
                    1 -> Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Home Content",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF654321)
                        )
                    }

                    2 -> Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Plots Content",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF654321)
                        )
                    }

                    3 -> Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Tasks Content",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF654321)
                        )
                    }

                    4 -> Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Earnings Content",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF654321)
                        )
                    }

                    5 -> Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Profile Content",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF654321)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MaterialTheme {
        MainScreen(onNavigate = {})
    }
}