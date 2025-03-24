package com.dinh.feature.ui.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dinh.feature.navigation.AppDestination


@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(), id: String, onNavigate: (AppDestination) -> Unit
) {
    DetailContent(id)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(colection: String) {
    Surface {
        Column {
            TopAppBar(title = { Text(text = "$colection") }, navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")

                }
            }, actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "menu")

                }
            })
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {

            }
        }


    }

}