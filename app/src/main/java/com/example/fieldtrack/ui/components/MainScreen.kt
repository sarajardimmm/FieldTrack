package com.example.fieldtrack.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fieldtrack.navigation.Routes
import com.example.fieldtrack.navigation.MyApp
import com.example.fieldtrack.ui.components.buttons.CustomFloatingActionButton

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            if (destination?.hasRoute<Routes.History>() == true ||
                destination?.hasRoute<Routes.Zones>() == true ||
                destination?.hasRoute<Routes.Products>() == true
            ) {
                BottomBar(navController = navController)
            }
        },
        floatingActionButton = {
            if (destination?.hasRoute<Routes.History>() == true) {
                CustomFloatingActionButton(
                    onClick = { navController.navigate(Routes.LogEntryForm()) }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add log entry")
                }
            } else if (destination?.hasRoute<Routes.Zones>() == true) {
                CustomFloatingActionButton(
                    onClick = { navController.navigate(Routes.ZoneForm()) }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add zone")
                }
            } else if (destination?.hasRoute<Routes.Products>() == true) {
                CustomFloatingActionButton(
                    onClick = { navController.navigate(Routes.ProductForm()) }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add product")
                }
            }
        }
    ) { innerPadding ->
        MyApp(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
