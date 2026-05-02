package com.example.fieldtrack.ui.components

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.fieldtrack.R
import com.example.fieldtrack.navigation.Routes
import com.example.fieldtrack.navigation.MyApp
import com.example.fieldtrack.ui.components.buttons.CustomFloatingActionButton

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination

    val title = navBackStackEntry.getScreenTitle()
    val isTopLevel = destination.isTopLevel()
    val fabAction = destination.getFabAction()

    val onBack: (() -> Unit)? = if (!isTopLevel) {
        { navController.popBackStack() }
    } else null

    Scaffold(
        topBar = {
            AppTopBar(
                title = title,
                onBack = onBack
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        floatingActionButton = {
            fabAction?.let { route ->
                CustomFloatingActionButton(
                    onClick = { navController.navigate(route) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add, 
                        contentDescription = stringResource(R.string.title_log_form),
                        tint = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background // Explicit background to prevent blending
    ) { innerPadding ->
        MyApp(
            navController = navController,
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        )
    }
}

@Composable
private fun NavBackStackEntry?.getScreenTitle(): String {
    val destination = this?.destination
    return when {
        destination?.hasRoute<Routes.History>() == true -> stringResource(R.string.title_log_history)
        destination?.hasRoute<Routes.LogEntryDetail>() == true -> stringResource(R.string.title_log_detail)
        destination?.hasRoute<Routes.LogEntryForm>() == true -> {
            val route = this.toRoute<Routes.LogEntryForm>()
            if (route.logEntryId != null) stringResource(R.string.title_edit_log_form)
            else stringResource(R.string.title_log_form)
        }
        destination?.hasRoute<Routes.Products>() == true -> stringResource(R.string.title_product_list)
        destination?.hasRoute<Routes.ProductDetail>() == true -> stringResource(R.string.title_product_detail)
        destination?.hasRoute<Routes.ProductForm>() == true -> {
            val route = this.toRoute<Routes.ProductForm>()
            if (route.productId != null) stringResource(R.string.title_edit_product)
            else stringResource(R.string.title_add_product)
        }
        destination?.hasRoute<Routes.Zones>() == true -> stringResource(R.string.title_zone_list)
        destination?.hasRoute<Routes.ZoneDetail>() == true -> stringResource(R.string.title_zone_detail)
        destination?.hasRoute<Routes.ZoneForm>() == true -> {
            val route = this.toRoute<Routes.ZoneForm>()
            if (route.zoneId != null) stringResource(R.string.title_edit_zone)
            else stringResource(R.string.title_add_zone)
        }
        else -> ""
    }
}

private fun NavDestination?.isTopLevel(): Boolean {
    val topLevelRoutes = listOf(
        Routes.History::class,
        Routes.Products::class,
        Routes.Zones::class
    )
    return topLevelRoutes.any { this?.hasRoute(it) == true }
}

private fun NavDestination?.getFabAction(): Routes? {
    return when {
        this?.hasRoute<Routes.History>() == true -> Routes.LogEntryForm()
        this?.hasRoute<Routes.Zones>() == true -> Routes.ZoneForm()
        this?.hasRoute<Routes.Products>() == true -> Routes.ProductForm()
        else -> null
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
