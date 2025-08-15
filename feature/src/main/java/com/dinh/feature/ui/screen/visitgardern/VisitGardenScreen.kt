package com.dinh.feature.ui.screen.visitgardern

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dinh.feature.R
import com.dinh.feature.navigation.AppDestination
import com.dinh.feature.ui.component.TitleScreen

@Composable
fun VisitGardenScreen(
    viewModel: VisitGardenViewModel = hiltViewModel(),
    onNavigate: (AppDestination) -> Unit
) {

    LaunchedEffect(viewModel.navigator) {
        viewModel.navigator.collect {
            onNavigate(it)
        }
    }

    VisitGardenContent(onClickVisitGarden = {
        viewModel.onClickSelectCrop()
    })
}

@Composable
fun VisitGardenContent(onClickVisitGarden : ()-> Unit) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Set the status bar color
            window.statusBarColor = Color(0xFF9EE1EC).toArgb()
            // You can also control the status bar icon appearance (light/dark)
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                false // Example: for dark icons
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable {
            onClickVisitGarden.invoke()
        }) {

        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            contentDescription = null,
            painter = painterResource(R.drawable.visit_garden_background)
        )
//        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//            Image(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(screenHeight / 8f),
//                contentDescription = null,
//                contentScale = ContentScale.FillWidth,
//                painter = painterResource(R.drawable.screen_title)
//            )
//            Text("Visit garden", color = Color.White, fontSize = 24.sp)
//        }
        TitleScreen("Visit garden")


    }

}

@Preview
@Composable
fun previewVisitGardenScreen() {
    VisitGardenContent({})


}