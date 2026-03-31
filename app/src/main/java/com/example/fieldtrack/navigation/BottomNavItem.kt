package com.example.fieldtrack.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.fieldtrack.R

data class BottomNavItem(
    val route: String,
    @StringRes val labelRes: Int,
    val icon: ImageVector,
) {
    companion object {
        val bottomNavItems: List<BottomNavItem> = listOf(
            BottomNavItem(
                route = Routes.HISTORY,
                labelRes = R.string.nav_history,
                icon = Icons.Default.List
            ),
            BottomNavItem(
                route = Routes.ZONES,
                labelRes = R.string.nav_zones,
                icon = Icons.Default.Place
            ),
            BottomNavItem(
                route = Routes.PRODUCTS,
                labelRes = R.string.nav_products,
                icon = Icons.Default.Agriculture
            )
        )
    }
}