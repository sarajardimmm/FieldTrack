package com.example.fieldtrack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fieldtrack.feature.logentry.form.LogEntryFormRoute
import com.example.fieldtrack.feature.logentry.detail.LogEntryDetailRoute
import com.example.fieldtrack.feature.logentry.history.LogEntryListRoute
import com.example.fieldtrack.feature.product.detail.ProductDetailRoute
import com.example.fieldtrack.feature.product.form.ProductFormRoute
import com.example.fieldtrack.feature.product.list.ProductListRoute
import com.example.fieldtrack.feature.zone.detail.ZoneDetailRoute
import com.example.fieldtrack.feature.zone.form.ZoneFormRoute
import com.example.fieldtrack.feature.zone.list.ZoneListRoute

@Composable
fun MyApp(modifier: Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.History,
        modifier = modifier
    ) {
        composable<Routes.History> {
            LogEntryListRoute(
                onLogEntryClick = { logEntryId ->
                    navController.navigate(Routes.LogEntryDetail(logEntryId))
                }
            )
        }
        composable<Routes.LogEntryDetail> {
            LogEntryDetailRoute(
                onEditClick = { logEntryId ->
                    navController.navigate(Routes.LogEntryForm(logEntryId))
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable<Routes.LogEntryForm> {
            LogEntryFormRoute(onNavigateBack = {
                navController.popBackStack()
            })
        }
        composable<Routes.Products> {
            ProductListRoute(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onProductClick = { productId ->
                    navController.navigate(Routes.ProductDetail(productId))
                }
            )
        }
        composable<Routes.ProductDetail> {
            ProductDetailRoute(
                onEditClick = { productId ->
                    navController.navigate(Routes.ProductForm(productId))
                },
                onLogEntryClick = { logEntryId ->
                    navController.navigate(Routes.LogEntryDetail(logEntryId))
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable<Routes.ProductForm> {
            ProductFormRoute(onNavigateBack = {
                navController.popBackStack()
            })
        }
        composable<Routes.Zones> {
            ZoneListRoute(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onZoneClick = { zoneId ->
                    navController.navigate(Routes.ZoneDetail(zoneId))
                }
            )
        }
        composable<Routes.ZoneForm> {
            ZoneFormRoute(onNavigateBack = {
                navController.popBackStack()
            })
        }
        composable<Routes.ZoneDetail> {
            ZoneDetailRoute(
                onEditClick = { zoneId ->
                    navController.navigate(Routes.ZoneForm(zoneId))
                },
                onLogEntryClick = { logEntryId ->
                    navController.navigate(Routes.LogEntryDetail(logEntryId))
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}