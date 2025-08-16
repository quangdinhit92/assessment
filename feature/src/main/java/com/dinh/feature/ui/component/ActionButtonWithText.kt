package com.dinh.feature.ui.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dinh.feature.R


@Composable
fun ActionButtonWithText(
    imageSize:Float,
    modifier: Modifier = Modifier,
    backgroundColor: Long,
    borderColor: Long,
    imageId: Int,
    text: String = ""

) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(backgroundColor), shape = RoundedCornerShape(size = 10.dp)
            )
            .border(
                width = 5.dp, color = Color(borderColor), shape = RoundedCornerShape(size = 10.dp)
            ),
    ) {

        Row(
            modifier = Modifier
                .padding(20.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(
                alignment = Alignment.Center,
                modifier=Modifier  .width(imageSize.dp),
                painter = painterResource(imageId),
                contentDescription = null,
                contentScale = ContentScale.Fit,

                )

            Text(

                text = text,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically).weight(1f)
            )
        }


    }
}

@Composable
@Preview
fun previewActionButtonWithtext() {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    ActionButtonWithText(
        40f,
        Modifier,

        0xFFBBDEFB, 0xff669bbc, R.drawable.ic_humid, "Tomato"
    )
}