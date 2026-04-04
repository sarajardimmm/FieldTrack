package com.example.fieldtrack.ui.components

import android.content.res.Configuration
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fieldtrack.navigation.BottomNavItem
import com.example.fieldtrack.navigation.Routes
import com.example.fieldtrack.ui.theme.FieldTrackTheme

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination

    BottomBarContent(
        destination = destination,
        onItemClick = { route ->
            navController.navigate(route) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}

@Composable
fun BottomBarContent(
    destination: NavDestination?,
    onItemClick: (Any) -> Unit
) {
    NavigationBar {
        BottomNavItem.bottomNavItems.forEach { item ->
            val label = stringResource(item.labelRes)
            NavigationBarItem(
                selected = destination?.hasRoute(item.route::class) == true,
                onClick = { onItemClick(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = label
                    )
                },
                label = {
                    Text(label)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

@Preview(
    name = "Bottom Bar - Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Bottom Bar - Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun BottomBarPreview() {
    FieldTrackTheme {
        BottomBarContent(
            destination = null,
            onItemClick = {}
        )
    }
}
