package com.dinh.feature.ui.component.modal

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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
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

private object RequestModalColors {
    val DarkGreen: Color = Color(0xFF084808)
    val MediumGreen: Color = Color(0xFF69c969)
    val LightGreen: Color = Color(0xFF00a86b)

    val TurquoiseStart = Color(0xFF1BA3C6)
    val TurquoiseEnd = Color(0xFF4FC3F7)
    val TurquoiseMid = Color(0xFF29B6D1)

    val BackgroundGradient = Brush.verticalGradient(
        colors = listOf(TurquoiseStart, TurquoiseMid, TurquoiseEnd)
    )

    val ButtonShadowColor = Color.Black.copy(alpha = 0.3f)
}

fun Modifier.customShadow(
    color: Color = Color.Black.copy(alpha = 0.3f),
    cornerRadius: Dp = 0.dp,
    blurRadius: Dp = 12.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Float = 0f,
) = this.then(
    Modifier.drawBehind {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()

        frameworkPaint.color = android.graphics.Color.TRANSPARENT
        frameworkPaint.setShadowLayer(
            blurRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            color.copy(alpha = 0.4f).toArgb()
        )

        val left = 0f - spread
        val top = 0f - spread
        val right = size.width + spread
        val bottom = size.height + spread

        drawIntoCanvas { canvas ->
            canvas.drawRoundRect(
                left,
                top,
                right,
                bottom,
                cornerRadius.toPx(),
                cornerRadius.toPx(),
                paint
            )
        }
    }
)

@Composable
fun RequestModalButton(
    modifier: Modifier = Modifier,
    borderWidth: Dp = 2.dp,
    text: String = "Sample Button",
    cornerRadius: Dp = 8.dp,
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
            .background(RequestModalColors.DarkGreen, shape)
            .padding(borderWidth)
            .background(RequestModalColors.MediumGreen, shape)
            .padding(borderWidth)
            .background(RequestModalColors.LightGreen, shape)
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
    options: Array<Option> = emptyArray(),
    onPressConfirm: () -> Unit = {},
) {
    BaseModal(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        title = title,
        background = RequestModalColors.BackgroundGradient,
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
                                    color = RequestModalColors.ButtonShadowColor,
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
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                RequestModalButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .customShadow(
                            color = RequestModalColors.ButtonShadowColor,
                            offsetX = 0.dp,
                            offsetY = 4.dp,
                            blurRadius = 12.dp
                        ),
                    text = "Sample Button 3"
                )

                RequestModalButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .customShadow(
                            color = RequestModalColors.ButtonShadowColor,
                            offsetX = 0.dp,
                            offsetY = 4.dp,
                            blurRadius = 12.dp
                        ),
                    text = "Sample Button 4"
                )
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