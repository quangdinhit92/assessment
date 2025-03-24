package com.dinh.feature.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dinh.feature.UiModel.UiModelSearch

@Composable
fun SearchItem(item: UiModelSearch, onClick: (UiModelSearch) -> Unit) {
    Card(modifier = Modifier
        .clickable { onClick.invoke(item) }
        .fillMaxWidth()
        .padding(10.dp), elevation = CardDefaults.cardElevation(5.dp)) {
        Column {

            Image(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                painter = rememberAsyncImagePainter(model = item.artworkUrl600),
                contentDescription = null
            )
            Text(text = item.artistName, modifier = Modifier.padding(10.dp))
        }


    }

}

@Preview
@Composable
fun previewSearchItem() {
    SearchItem(UiModelSearch(
        artistId = 1000L,
        collectionId = 100L,
        artistName = "Abba",
        collectionName = "Hilt",
        artworkUrl600 = "https://is1-ssl.mzstatic.com/image/thumb/Music125/v4/89/77/46/897746b8-3d89-c551-4359-7aa440b67b69/mzi.clkeppje.jpg/60x60bb.jpg",
    ), {})
}