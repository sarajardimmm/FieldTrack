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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fieldtrack.navigation.Routes
import com.example.fieldtrack.navigation.MyApp

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in setOf(Routes.HISTORY, Routes.ZONES, Routes.PRODUCTS)) {
                BottomBar(navController = navController)
            }
        },
        floatingActionButton = {
            when (currentRoute) {
                Routes.HISTORY -> {
                    FloatingActionButton(
                        onClick = { navController.navigate(Routes.LOG_ENTRY_FORM) },
                        containerColor = MaterialTheme.colorScheme.tertiary,
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add log entry")
                    }
                }
                Routes.ZONES -> {
                    FloatingActionButton(
                        onClick = { navController.navigate(Routes.ZONES) }
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add zone")
                    }
                }
                Routes.PRODUCTS -> {
                    FloatingActionButton(
                        onClick = { navController.navigate(Routes.PRODUCTS) }
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add product")
                    }
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