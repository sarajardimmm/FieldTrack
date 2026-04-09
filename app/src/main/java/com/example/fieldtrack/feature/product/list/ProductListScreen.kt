package com.example.fieldtrack.feature.product.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fieldtrack.R
import com.example.fieldtrack.data.db.model.Product
import com.example.fieldtrack.ui.components.AppTopBar
import com.example.fieldtrack.ui.components.EmptyStateCard
import com.example.fieldtrack.ui.components.ListItem
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun ProductListScreen(
    onNavigateBack: () -> Unit,
    onProductClick: (Long) -> Unit,
    products: List<Product>
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.title_product_list),
                onBack = onNavigateBack
            )
        }
    ) { innerPadding ->
        ProductListContent(
            onProductClick = onProductClick,
            products = products,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ProductListContent(
    onProductClick: (Long) -> Unit,
    products: List<Product>,
    modifier: Modifier = Modifier
) {
    if (products.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            EmptyStateCard(
                title = stringResource(R.string.title_no_products),
                description = stringResource(R.string.description_no_products)
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(products) { product ->
                ProductListItem(
                    product = product,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onProductClick(product.id) }
                )
            }
        }
    }
}

@Composable
fun ProductListItem(product: Product, modifier: Modifier = Modifier) {
    ListItem(
        modifier = modifier,
        contentLeft = {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium
            )

            product.category?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}

@Preview(
    name = "Product List - Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Product List - Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ProductListScreenPreview() {
    val products = listOf(
        Product(id = 1, name = "Copper Sulfate", category = "Fungicide", defaultReapplyDays = 14, storageLocation = "Shed B", notes = ""),
        Product(id = 2, name = "Roundup", category = "Herbicide", defaultReapplyDays = null, storageLocation = null, notes = ""),
    )
    FieldTrackTheme {
        Surface {
            ProductListScreen(
                onNavigateBack = {},
                onProductClick = {},
                products = products
            )
        }
    }
}
