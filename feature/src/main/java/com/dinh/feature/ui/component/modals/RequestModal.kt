package com.dinh.feature.ui.component.modals

import BaseModal
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import customShadow

@Composable
fun RequestModalButton(
    modifier: Modifier = Modifier,
    borderWidth: Dp = 2.dp,
    text: String = "Sample Button",
    cornerRadius: Dp = 12.dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    imageSize: Dp = 24.dp,
    image: Int? = null,
    fontSize: TextUnit = 12.sp,
    onClick: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(ModalTheme.DarkGreen, shape)
            .padding(borderWidth)
            .background(ModalTheme.MediumGreen, shape)
            .padding(borderWidth)
            .background(ModalTheme.LightGreen, shape)
            .let { if (onClick != null) it.clickable { onClick() } else it }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 8.dp),
        ) {
            if (image != null) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier.size(imageSize),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Fit,
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = if (image != null) TextAlign.Start else TextAlign.Center,
                text = text,
                fontSize = fontSize,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.5f),
                        offset = Offset(1f, 1f),
                        blurRadius = 2f
                    )
                ),
            )
        }
    }
}

data class Option(
    val onPress: () -> Unit,
    val title: String,
)

@Composable
fun RequestModal(
    modifier: Modifier = Modifier,
    title: String = "Request",
    subTitle: String = "Sub Title",
    description: String = "Description",
    textConfirm: String = "Confirm",
    textDone: String = "Done",
    textWait: String = "Wait",
    options: Array<Option> = emptyArray(),
    onPressConfirm: () -> Unit = {},
    onPressDone: () -> Unit = {},
    onPressWait: () -> Unit = {},
) {
    BaseModal(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        title = title,
        background = ModalTheme.BackgroundGradient,
        textConfirm = textConfirm,
        onPressConfirm = onPressConfirm
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = subTitle,
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.6f),
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                ),
                modifier = Modifier.fillMaxWidth()
            )

            if (options.isNotEmpty()) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    options.forEach { option ->
                        RequestModalButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .customShadow(
                                    color = ModalTheme.ButtonShadowColor,
                                    offsetX = 0.dp,
                                    offsetY = 4.dp,
                                    blurRadius = 12.dp
                                ),
                            text = option.title,
                            onClick = option.onPress
                        )
                    }
                }
            }

            Text(
                text = description,
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.6f),
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .customShadow(
                            color = ModalTheme.ButtonShadowColor,
                            cornerRadius = 12.dp,
                            blurRadius = 12.dp,
                            offsetY = 4.dp
                        )
                        .background(ModalTheme.DarkBrown, RoundedCornerShape(12.dp))
                        .padding(2.dp)
                        .background(ModalTheme.MediumBrown, RoundedCornerShape(12.dp))
                        .padding(2.dp)
                        .background(
                            ModalTheme.ButtonConfirmGradient,
                            RoundedCornerShape(12.dp)
                        )
                        .clickable { onPressWait() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = textWait,
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black.copy(alpha = 0.5f),
                                offset = Offset(1f, 1f),
                                blurRadius = 2f
                            )
                        ),
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .customShadow(
                            color = ModalTheme.ButtonShadowColor,
                            cornerRadius = 12.dp,
                            blurRadius = 12.dp,
                            offsetY = 4.dp
                        )
                        .background(ModalTheme.DarkGreen, RoundedCornerShape(12.dp))
                        .padding(2.dp)
                        .background(ModalTheme.MediumGreen, RoundedCornerShape(12.dp))
                        .padding(2.dp)
                        .background(ModalTheme.LightGreen, RoundedCornerShape(12.dp))
                        .clickable { onPressDone() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = textDone,
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black.copy(alpha = 0.5f),
                                offset = Offset(1f, 1f),
                                blurRadius = 2f
                            )
                        ),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun PreviewRequestModal() {
    MaterialTheme {
        RequestModal(
            modifier = Modifier.fillMaxWidth(),
            title = "Sample Title",
            subTitle = "Sample Subtitle",
            description = "This is a sample description for the modal.",
            options = arrayOf(
                Option(title = "Option A", onPress = { println("Pressed A") }),
                Option(title = "Option B", onPress = { println("Pressed B") }),
                Option(title = "Option C", onPress = { println("Pressed C") }),
            ),
            onPressConfirm = { println("Confirm button pressed!") }
        )
    }
}