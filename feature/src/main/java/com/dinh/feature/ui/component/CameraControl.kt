package com.dinh.feature.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dinh.feature.R

@Composable
fun CameraControl() {
    val outerCircle = 100.dp
    Box(
        modifier = Modifier
            .size(outerCircle)
            .background(
                color = Color(0xff1B6D3A), shape = CircleShape
            ), contentAlignment = Alignment.Center
    ) {


        Image(
            painter = painterResource(R.drawable.ic_keyboard_up),
            contentDescription = null,
            modifier = Modifier.align(
                Alignment.TopCenter
            )
        )

        Image(
            painter = painterResource(R.drawable.ic_keyboard_down),
            contentDescription = null,
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        Image(
            painter = painterResource(R.drawable.ic_keyboard_left),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Image(
            painter = painterResource(R.drawable.ic_keyboard_right),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterEnd)
        )

        Box(
            modifier = Modifier
                .size(outerCircle / 2)
                .background(color = Color.Gray, shape = CircleShape)
        )

    }
}

@Preview
@Composable
fun PreviewCameraControl() {
    CameraControl()
}

@Composable
fun CameraButton(@DrawableRes imageId: Int, text: String, modifier: Modifier) {

    Surface(
        shape = CircleShape, color = Color(0xff1B6D3A), modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.aspectRatio(1f)

        ) {
            Image(
                painter = painterResource(imageId),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }


}

@Preview
@Composable
fun PreviewCameraButton() {
    CameraButton(R.drawable.ic_water, "water", Modifier)
}


@Composable
fun CameraControlPanel(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xff0C5432))
            .padding(10.dp)

    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CameraControl()
            Row(
                modifier = Modifier.weight(1f)
            ) {
                CameraButton(
                    R.drawable.ic_water, "Water", Modifier
                        .weight(1f)
                        .padding(16.dp)
                )
                CameraButton(
                    R.drawable.ic_light, "Light", Modifier
                        .weight(1f)
                        .padding(16.dp)
                )
                CameraButton(
                    R.drawable.ic_temp_auto, "ferilize", Modifier
                        .weight(1f)
                        .padding(16.dp)
                )

            }

        }

        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .wrapContentSize()
                .background(color = Color(0xff1B6D3A), shape = RoundedCornerShape(30.dp))
                .padding(16.dp)
        ) {

            Image(
                painter = painterResource(R.drawable.ic_temp_inc),
                contentDescription = null,
                modifier = Modifier.wrapContentSize()
            )
            Spacer(modifier = Modifier.width(30.dp))
            Image(
                painter = painterResource(R.drawable.ic_temp_dec),
                contentDescription = null,
                modifier = Modifier.wrapContentSize()
            )

        }

    }

}

@Preview
@Composable
fun PreviewCameraControlPanel() {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    CameraControlPanel(
        Modifier
            .fillMaxWidth()
            .height(screenHeight / 3f)
    )
}

