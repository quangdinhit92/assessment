package com.dinh.feature.ui.screen.selectcrop

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.dinh.feature.R
import com.dinh.feature.ui.component.TitleScreen
import com.dinh.feature.ui.screen.visitgardern.VisitGardenViewModel

@Composable
fun SelectCropScreen(viewModel: SelectCropViewModel = hiltViewModel()) {

    val crops = viewModel.output.crop.collectAsState()

    SelectCropContent(arrayOf<Int>(1, 2, 3, 4, 5, 6, 9))
}

@Composable
fun SelectCropContent(cropitems: Array<Int>) {
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

    Box(modifier = Modifier.fillMaxSize()) {
        val spacing = 20.dp
        val numberOfColumn = 3
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            contentDescription = null,
            painter = painterResource(R.drawable.select_crop_background)
        )

        Column(modifier = Modifier.fillMaxSize()) {
//            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//                Image(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(screenHeight / 8f),
//                    contentDescription = null,
//                    contentScale = ContentScale.FillWidth,
//                    painter = painterResource(R.drawable.screen_title)
//                )
//                Text("Select crop", color = Color.White, fontSize = 24.sp)
//            }
            TitleScreen("Select crop")
            LazyVerticalGrid(


                columns = GridCells.Adaptive(minSize = 100.dp), // số cột
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEFB8C8)),
                contentPadding = PaddingValues(horizontal = spacing, vertical = spacing),
                horizontalArrangement = Arrangement.spacedBy(spacing),
                verticalArrangement = Arrangement.spacedBy(spacing)
            ) {
                items(cropitems) { item ->
                    Box(
                        modifier = Modifier.aspectRatio(1.0f)
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),

                            contentDescription = null,
                            painter = painterResource(R.drawable.crop_item)
                        )
                    }
                }
            }


        }


    }

}

@Preview
@Composable
fun previewSelectCropScreen() {
    SelectCropContent(arrayOf<Int>(1, 2, 3))

}