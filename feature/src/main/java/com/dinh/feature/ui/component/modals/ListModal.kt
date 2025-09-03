package com.dinh.feature.ui.component.modals

import BaseModal
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun <T> ListModal(
    modifier: Modifier = Modifier,
    items: List<T>,
    itemContent: @Composable (T) -> Unit = { item ->
        DefaultItemContent(item = item)
    },
    title: String = "Request",
    maxHeight: Int = 200
) {
    BaseModal(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        title = title,
        background = ModalTheme.BackgroundGradient,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(maxHeight.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items) { item ->
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemContent(item)
                }
            }
        }
    }
}

@Composable
private fun <T> DefaultItemContent(item: T) {
    Text(
        text = item.toString(),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface
    )
}

data class SampleItem(
    val id: Int,
    val name: String,
    val description: String? = null
)

@Preview(showBackground = true, widthDp = 320)
@Composable
fun PreviewListModal() {
    val sampleItems = listOf(
        SampleItem(1, "Option 1", "First option"),
        SampleItem(2, "Option 2", "Second option"),
    )

    MaterialTheme {
        ListModal(
            modifier = Modifier.fillMaxWidth(),
            title = "Items List",
            items = sampleItems,
            itemContent = { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        item.description?.let { desc ->
                            Text(
                                text = desc,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        )
    }
}