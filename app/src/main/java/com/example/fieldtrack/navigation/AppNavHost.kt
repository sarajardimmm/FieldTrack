package com.example.fieldtrack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.fieldtrack.feature.logentry.form.LogEntryFormRoute
import com.example.fieldtrack.feature.logentry.detail.LogEntryDetailRoute
import com.example.fieldtrack.feature.logentry.history.LogEntryListRoute
import com.example.fieldtrack.feature.product.list.ProductListRoute
import com.example.fieldtrack.feature.zone.detail.ZoneDetailRoute
import com.example.fieldtrack.feature.zone.form.ZoneFormRoute
import com.example.fieldtrack.feature.zone.list.ZoneListRoute

@Composable
fun MyApp(modifier: Modifier, navController: NavHostController) {
    val startRoute = Routes.HISTORY
    NavHost(
        navController = navController,
        startDestination = startRoute,
        modifier = modifier
    ) {
        composable(Routes.HISTORY) {
            LogEntryListRoute(
                onLogEntryClick = { logEntryId ->
                    navController.navigate(Routes.logEntryDetail(logEntryId))
                }
            )
        }
        composable(
            route = Routes.LOG_ENTRY_DETAIL,
            arguments = listOf(navArgument("logEntryId") { type = NavType.LongType })
        ) {
            LogEntryDetailRoute(
                onEditClick = { logEntryId ->
                    navController.navigate(Routes.logEntryForm(logEntryId))
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )

        }
        composable(
            route = Routes.LOG_ENTRY_FORM,
            arguments = listOf(navArgument("logEntryId") {
                type = NavType.LongType
                defaultValue = -1L
            })
        ) {
            LogEntryFormRoute(onNavigateBack = {
                navController.popBackStack()
            })
        }
        composable(Routes.PRODUCTS) {
            ProductListRoute(onNavigateBack = {
                navController.popBackStack()
            })
        }
        composable(
            route =Routes.ZONES) {
            ZoneListRoute(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onZoneClick =  { zoneId ->
                    navController.navigate(Routes.zoneDetail(zoneId = zoneId))
                }
            )
        }
        composable(
            route = Routes.ZONE_FORM,
            arguments = listOf(navArgument("zoneId") {
                type = NavType.LongType
                defaultValue = -1L
            })
        ) {
            ZoneFormRoute(onNavigateBack = {
                navController.popBackStack()
            })
        }
        composable(
            route = Routes.ZONE_DETAIL,
            arguments = listOf(navArgument("zoneId") { type = NavType.LongType })
        ) {
            ZoneDetailRoute(
                onEditClick = { zoneId ->
                    navController.navigate(Routes.zoneForm(zoneId))
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}