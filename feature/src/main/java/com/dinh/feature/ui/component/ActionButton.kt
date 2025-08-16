package com.dinh.feature.ui.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dinh.feature.R


@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    width: Float,
    height: Float,
    backgroundColor: Long,
    borderColor: Long,
    imageId: Int,
) {

    Box(
        modifier = modifier
            .size(width = width.dp, height = height.dp)
            .background(
                color = Color(backgroundColor), shape = RoundedCornerShape(size = 10.dp)
            )
            .border(
                width = 10.dp, color = Color(borderColor), shape = RoundedCornerShape(size = 10.dp)
            )
    ) {

        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(5.dp)
        )

    }
}

@Composable
@Preview
fun previewActionButton() {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    ActionButton(
        Modifier,
        screenWidth / 4f,
        screenWidth / 4f,
        0xFFBBDEFB,
        0xff669bbc,
        R.drawable.ic_humid
    )
}