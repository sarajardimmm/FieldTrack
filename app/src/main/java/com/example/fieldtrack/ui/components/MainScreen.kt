package com.example.fieldtrack.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
                FloatingActionButton(
                    onClick = { navController.navigate(Routes.LogEntryForm()) },
                    containerColor = MaterialTheme.colorScheme.tertiary,
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add log entry")
                }
            } else if (destination?.hasRoute<Routes.Zones>() == true) {
                FloatingActionButton(
                    onClick = { navController.navigate(Routes.ZoneForm()) },
                    containerColor = MaterialTheme.colorScheme.tertiary,
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add zone")
                }
            } else if (destination?.hasRoute<Routes.Products>() == true) {
                FloatingActionButton(
                    onClick = { navController.navigate(Routes.Products) } // TODO: Add Product Form
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
