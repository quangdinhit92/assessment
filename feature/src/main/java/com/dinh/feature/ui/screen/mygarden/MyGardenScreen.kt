package com.dinh.feature.ui.screen.mygarden

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.dinh.feature.R
import com.dinh.feature.ui.component.ActionButton
import com.dinh.feature.ui.component.ActionButtonWithText
import com.dinh.feature.ui.component.MyGardenInfo
import com.dinh.feature.ui.component.TitleScreen

@Composable
fun MyGardenScreen() {

    MyGardenContent()
}

@Composable
fun MyGardenContent() {

    val itemsList = (1..5).map { "Item $it" }

    val screenWidth = LocalConfiguration.current.screenWidthDp
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            modifier = Modifier.fillMaxSize(), contentDescription = null, painter = painterResource(
                R.drawable.my_garden_background
            ), contentScale = ContentScale.Crop
        )

        MyGardenInfo("Carot")

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.Center)
        ) {

            val (belowGarden, belowMainButton, bottomStart, bottomEnd, center) = createRefs()
            Image(
                modifier = Modifier.constrainAs(center) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)

                }, painter = painterResource(R.drawable.my_garden), contentDescription = null
            )


            ActionButtonWithText(
                screenWidth / 8f, modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .constrainAs(belowGarden) {
                        top.linkTo(center.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },

                0xFF169330, 0xff669bbc, R.drawable.ic_tomato, "Water the Tomato"
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(belowMainButton) {
                        top.linkTo(belowGarden.bottom)
                    }) {

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    item {
                        ActionButton(
                            Modifier,
                            screenWidth / 4f,
                            screenWidth / 4f,
                            0x00BBDEFB,
                            0x00669bbc,
                            R.drawable.ic_plan_the_crop
                        )
                    }
                    item {
                        ActionButton(
                            Modifier,
                            screenWidth / 4f,
                            screenWidth / 4f,
                            0x00BBDEFB,
                            0x00669bbc,
                            R.drawable.ic_harvet_the_crop
                        )
                    }
                    item {
                        ActionButton(
                            Modifier,
                            screenWidth / 4f,
                            screenWidth / 4f,
                            0x00BBDEFB,
                            0x00669bbc,
                            R.drawable.ic_livestream_ther_crop
                        )
                    }
                }


            }

        }

    }

}

@Composable
//@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true, device = Devices.TABLET)
@Preview(uiMode = UI_MODE_NIGHT_NO, showSystemUi = true, device = Devices.PHONE)
fun previewMyGarden() {
    MyGardenContent()

}