package com.dinh.feature.ui.screen.mygarden

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.dinh.feature.R

@Composable
fun MyGardenScreen() {
}

@Composable
fun MyGardenContent() {
    Box(modifier = Modifier.fillMaxSize())
    {
        Image(modifier = Modifier.fillMaxSize(), contentDescription = null, painter = painterResource(
            R.drawable.my_garden_background))
    }

}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true, device = Devices.TABLET)
@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true, device = Devices.PHONE)
fun previewMyGarden() {
    MyGardenContent()

}