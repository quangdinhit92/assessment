package com.dinh.feature.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dinh.feature.R

@Composable
fun TitleScreen(title:String) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val ratioTitleHeight=8
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
    {
        Image(
            painter = painterResource(R.drawable.screen_title),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(screenHeight/ratioTitleHeight)
        )
        Text(text = title, fontSize = 24.sp, color = androidx.compose.ui.graphics.Color.White)

    }
}


@Composable
//@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true, device = Devices.TABLET)
//@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true, device = Devices.PHONE)
@Preview
fun PreviewTitle() {
    TitleScreen("title")
}