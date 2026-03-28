package com.example.fieldtrack.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector,


) {
    companion object {
        val bottomNavItems: List<BottomNavItem> = listOf(
            BottomNavItem(
                route = Routes.HISTORY,
                label = "History",
                icon = Icons.Default.List
            ),
            BottomNavItem(
                route = Routes.ZONES,
                label = "Zones",
                icon = Icons.Default.Place
            ),
            BottomNavItem(
                route = Routes.PRODUCTS,
                label = "Products",
                icon = Icons.Default.Agriculture
            )
        )    }
}