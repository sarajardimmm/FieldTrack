package com.example.fieldtrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fieldtrack.feature.logEntryForm.LogEntryFormRoute
import com.example.fieldtrack.feature.logentrydetail.LogEntryDetailRoute
import com.example.fieldtrack.feature.logentryhistory.LotEntryListRoute

@Composable
    fun MyApp() {
        val navController = rememberNavController()
        val startRoute = "log_entry_list"
        NavHost(
            navController,
            startDestination = startRoute
        ) {
            composable("log_entry_list") {
                LotEntryListRoute(
                    onLogEntryClick = { logEntryId ->
                        navController.navigate("log_entry_details/$logEntryId")
                    },
                    onAddLogEntry = {
                        navController.navigate("log_entry_form")
                    }
                )
            }

            composable("log_entry_details/{logEntryId}") {
                LogEntryDetailRoute(onDelete = {
                    navController.navigate("log_entry_list")
                })
            }
            composable("log_entry_form") {
                LogEntryFormRoute(onNavigateBack = {
                    navController.navigate("log_entry_list")
                })
            }
    }
}