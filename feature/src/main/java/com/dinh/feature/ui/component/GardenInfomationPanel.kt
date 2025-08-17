package com.dinh.feature.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dinh.feature.R

@Composable
fun GardenInfomationPanel() {
}

@Composable
fun GardenInfomationPanelContent() {
    Box(
        Modifier
            .fillMaxWidth()
            .background(color = Color(0xff8F531B), shape = RoundedCornerShape(12.dp))
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()

                    .background(
                        color = Color.Green,
                        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                    )
                    .padding(10.dp)
            )

            {
                Text("Garden", color = Color.White, fontSize = 30.sp)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(20.dp),
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                    painter = painterResource(
                        R.drawable.ic_humid
                    )
                )
                Text(" 30% ", color = Color.White, fontSize = 20.sp)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(20.dp),
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                    painter = painterResource(
                        R.drawable.ic_calendar
                    )
                )
                Text(" 12 days ", color = Color.White, fontSize = 20.sp)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(20.dp),

                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                    painter = painterResource(
                        R.drawable.ic_temperature
                    )
                )
                Text(" 25 ℃ ", color = Color.White, fontSize = 20.sp)
            }
        }

    }

}

@Composable
@Preview
fun previewGardenInfomationPanel() {
    GardenInfomationPanelContent()
}