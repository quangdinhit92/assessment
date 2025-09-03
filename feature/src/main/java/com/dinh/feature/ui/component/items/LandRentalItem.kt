package com.dinh.feature.ui.component.items

import ModalTheme.LightBrown
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dinh.feature.R
import customShadow

data class LandRental(
    val id: Int,
    val name: String,
    val image: Int?,
)

object LandRentalTheme {
    val DarkGreen: Color = Color(0xFF084808)
    val MediumGreen: Color = Color(0xFF69c969)

    val DarkBrown: Color = Color(0xFF5a320f)
    val MediumBrown: Color = Color(0xFFbe8c3c)

    val ShadowColor = Color.Black.copy(alpha = 0.3f)

    val ButtonConfirmGradient = Brush.verticalGradient(
        colors = listOf(ModalTheme.MediumBrown, LightBrown)
    )
}

@Composable
fun LandRentalItem(
    landRental: LandRental,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .customShadow(
                color = LandRentalTheme.ShadowColor,
                cornerRadius = 12.dp,
                blurRadius = 12.dp,
                offsetY = 4.dp
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick ?: {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(LandRentalTheme.DarkGreen, RoundedCornerShape(12.dp))
                .padding(2.dp)
                .background(LandRentalTheme.MediumGreen, RoundedCornerShape(10.dp))
                .padding(2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (landRental.image != null) {
                    Image(
                        painter = painterResource(landRental.image),
                        contentDescription = "Land rental image",
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Fit,
                    )
                }

                Text(
                    modifier = Modifier.weight(1f),
                    text = landRental.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .width(80.dp)
                        .offset(y = 0.dp)
                        .customShadow(
                            color = LandRentalTheme.ShadowColor,
                            cornerRadius = 12.dp,
                            blurRadius = 12.dp,
                            offsetY = 4.dp
                        )
                        .background(LandRentalTheme.DarkBrown,  RoundedCornerShape(8.dp))
                        .padding(2.dp)
                        .background(LandRentalTheme.MediumBrown,  RoundedCornerShape(8.dp))
                        .padding(2.dp)
                        .background(LandRentalTheme.ButtonConfirmGradient,  RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Rent now",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun PreviewLandRentalItem() {
    MaterialTheme {
        LandRentalItem(
            landRental = LandRental(
                id = 1,
                name = "Fertile land perfect for organic farming",
                image = R.drawable.ic_humid
            )
        )
    }
}