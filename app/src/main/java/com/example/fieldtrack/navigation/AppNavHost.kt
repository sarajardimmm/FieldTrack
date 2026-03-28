package com.example.fieldtrack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fieldtrack.feature.logEntryForm.LogEntryFormRoute
import com.example.fieldtrack.feature.logentrydetail.LogEntryDetailRoute
import com.example.fieldtrack.feature.logentryhistory.LotEntryListRoute
import com.example.fieldtrack.feature.productlist.ProductListRoute
import com.example.fieldtrack.feature.zonelist.ZoneListRoute

@Composable
fun MyApp(modifier: Modifier, navController: NavHostController) {
    val startRoute = Routes.HISTORY
    NavHost(
        navController,
        startDestination = startRoute) {
        composable(Routes.HISTORY) {
            LotEntryListRoute(
                onLogEntryClick = { logEntryId ->
                    navController.navigate(Routes.logEntryDetail(logEntryId))
                },
                onAddLogEntry = {
                    navController.navigate(Routes.LOG_ENTRY_FORM)
                }
            )
        }
        composable(Routes.LOG_ENTRY_DETAIL) {
            LogEntryDetailRoute(onNavigateBack = {
                navController.popBackStack()
            })
        }
        composable(Routes.LOG_ENTRY_FORM) {
            LogEntryFormRoute(onNavigateBack = {
                navController.popBackStack()
            })
        }
        composable(Routes.PRODUCTS) {
            ProductListRoute(onNavigateBack = {
                navController.popBackStack()
            })
        }
        composable(Routes.ZONES) {
            ZoneListRoute(onNavigateBack = {
                navController.popBackStack()
            })
        }
    }
}