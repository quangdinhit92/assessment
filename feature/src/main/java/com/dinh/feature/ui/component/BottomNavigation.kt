package com.dinh.feature.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dinh.feature.R


data class NavItem(
    val id: Int,
    val name: String,
    val image: Int? = null,
)

private object BottomNavigationTheme {
    val DarkBrown: Color = Color(0xFF8B4513)
    val LightBrown: Color = Color(0xFFd7a046)
    val SelectedTextColor: Color = Color(0xFF654321)
    val UnselectedTextColor: Color = Color(0xFFB8860B)

    val ShadowColor = Color.Black.copy(alpha = 0.2f)
}

@Composable
fun BottomNavigationBar(
    items: List<NavItem>,
    selectedId: Int,
    onItemClick: (NavItem) -> Unit,
) {
    val cornerRadius = 40.dp
    val borderWidth = 3.dp
    val pillShape = RoundedCornerShape(cornerRadius)

    val tasksItem = items.find { it.name == "Tasks" }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .shadow(
                    elevation = 6.dp,
                    shape = pillShape,
                    ambientColor = BottomNavigationTheme.ShadowColor,
                    spotColor = BottomNavigationTheme.ShadowColor
                )
                .clip(pillShape)
                .border(
                    width = borderWidth, color = BottomNavigationTheme.DarkBrown, shape = pillShape
                )
                .background(BottomNavigationTheme.LightBrown, pillShape)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items.take(5).forEach { item ->
                    val isSelected = item.id == selectedId

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clickable { onItemClick(item) }) {
                        Box(
                            modifier = Modifier.size(30.dp)
                        ) {
                            if (item.image != null && item.name != "Tasks") {
                                Image(
                                    painter = painterResource(id = item.image),
                                    contentDescription = item.name,
                                    modifier = Modifier.size(30.dp),
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }

                        Text(
                            text = item.name,
                            color = if (isSelected) BottomNavigationTheme.SelectedTextColor
                            else BottomNavigationTheme.UnselectedTextColor,
                            fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(top = 2.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        tasksItem?.let { item ->
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-25).dp)
                    .clickable { onItemClick(item) }) {
                if (item.image != null) {
                    Image(
                        painter = painterResource(id = item.image),
                        contentDescription = item.name,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 360, heightDp = 200)
@Composable
fun PreviewBottomNavigationBar() {
    var selectedId by remember { mutableIntStateOf(1) }

    val items = listOf(
        NavItem(1, "Home", R.drawable.ic_home),
        NavItem(2, "Plots", R.drawable.ic_plots),
        NavItem(3, "Tasks", R.drawable.ic_tasks),
        NavItem(4, "Earnings", R.drawable.ic_earnings),
        NavItem(5, "Profile", R.drawable.ic_profile),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomNavigationBar(
            items = items, selectedId = selectedId, onItemClick = { clickedItem ->
                selectedId = clickedItem.id
            })
    }
}