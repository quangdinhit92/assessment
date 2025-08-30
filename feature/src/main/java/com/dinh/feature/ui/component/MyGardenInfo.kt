package com.dinh.feature.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
fun MyGardenInfo(planName: String) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xff8F531B), shape = RoundedCornerShape(12.dp))
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(text = planName, fontSize = 24.sp, color = Color.White)
                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .background(color = Color(0xff754212), shape = RoundedCornerShape(20.dp)).padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Image(
                                modifier = Modifier.height(25.dp),
                                painter = painterResource(R.drawable.ic_calendar),
                                contentScale = ContentScale.Fit,
                                contentDescription = null
                            )
                            Text("24", fontSize = 24.sp, color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()

                    .background(
                        shape = RoundedCornerShape(20.dp), color = Color(0xff754212)
                    )

            ) {

                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp),
                        painter = painterResource(R.drawable.ic_humid),
                        contentDescription = null
                    )
                    Text("50%", fontSize = 24.sp, color = Color.White)

                }

                Spacer(Modifier.width(10.dp))

                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp),
                        painter = painterResource(R.drawable.ic_temperature),
                        contentDescription = null
                    )
                    Text("25 ℃", fontSize = 24.sp, color = Color.White)

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp),
                        painter = painterResource(R.drawable.ic_crop_germinate),
                        contentDescription = null
                    )


                }


            }
        }
    }
}

@Composable
@Preview
fun previewMyGardenInfo() {
    MyGardenInfo("Carot")
}