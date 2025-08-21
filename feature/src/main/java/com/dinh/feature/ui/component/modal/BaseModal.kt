import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private object ModalColors {
    val DarkBrown: Color = Color(0xFF5a320f)
    val MediumBrown: Color = Color(0xFFbe8c3c)
    val LightBrown: Color = Color(0xFFd7a046)
    val SaddleBrown: Color = Color(0xFF8B4513)

    val TurquoiseStart = Color(0xFF1BA3C6)
    val TurquoiseEnd = Color(0xFF4FC3F7)
    val TurquoiseMid = Color(0xFF29B6D1)

    val BackgroundGradient = Brush.verticalGradient(
        colors = listOf(TurquoiseStart, TurquoiseMid, TurquoiseEnd)
    )

    val ButtonConfirmGradient = Brush.verticalGradient(
        colors = listOf(MediumBrown, LightBrown)
    )

    val ButtonShadowColor = Color.Black.copy(alpha = 0.3f)
}

fun Modifier.customShadow(
    color: Color,
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
fun BaseModal(
    modifier: Modifier = Modifier,
    borderWidth: Dp = 2.dp,
    cornerRadius: Dp = 16.dp,
    padding: Dp = 16.dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    background: Brush = ModalColors.BackgroundGradient,
    title: String = "Title",
    textConfirm: String = "Confirm",
    onPressConfirm: () -> Unit,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(ModalColors.DarkBrown, shape)
                .padding(borderWidth)
                .background(ModalColors.MediumBrown, shape)
                .padding(borderWidth)
                .background(ModalColors.LightBrown, shape)
                .padding(borderWidth)
                .background(ModalColors.SaddleBrown, shape)
                .padding(borderWidth)
                .background(background, shape)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp)
                    .wrapContentHeight()
                    .padding(top = 60.dp, bottom = 40.dp, start = 8.dp, end = 8.dp)
            ) {
                content()
            }
        }

        Box(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .customShadow(
                    color = ModalColors.ButtonShadowColor,
                    cornerRadius = cornerRadius,
                    blurRadius = 12.dp,
                    offsetY = 4.dp
                )
                .background(ModalColors.DarkBrown, shape)
                .padding(borderWidth)
                .background(ModalColors.MediumBrown, shape)
                .padding(borderWidth)
                .background(ModalColors.LightBrown, shape)
                .padding(borderWidth)
                .background(ModalColors.SaddleBrown, shape)
        ) {
            Text(
                text = title,
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Box(
            modifier = Modifier
                .widthIn(min = 150.dp)
                .height(40.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 0.dp)
                .customShadow(
                    color = ModalColors.ButtonShadowColor,
                    cornerRadius = cornerRadius,
                    blurRadius = 12.dp,
                    offsetY = 4.dp
                )
                .background(ModalColors.DarkBrown, shape)
                .padding(borderWidth)
                .background(ModalColors.MediumBrown, shape)
                .padding(borderWidth)
                .background(ModalColors.ButtonConfirmGradient, shape)
                .clickable { onPressConfirm() }
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = textConfirm,
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun PreviewBaseModal() {
    MaterialTheme {
        BaseModal(
            modifier = Modifier.fillMaxWidth(),
            title = "Sample Title",
            onPressConfirm = {
                println("Button pressed in preview")
            }
        ) {
            Text(
                text = "Sample Content",
                color = Color.White
            )
        }
    }
}