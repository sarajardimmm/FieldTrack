package com.example.fieldtrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fieldtrack.feature.applicationDetail.ApplicationDetailRoute
import com.example.fieldtrack.feature.applicationlist.ApplicationListRoute

@Composable
    fun MyApp() {
        val navController = rememberNavController()
        val startRoute = "application_list"
        NavHost(
            navController,
            startDestination = startRoute
        ) {
            composable("application_list") {
                ApplicationListRoute(
                    onApplicationClick = {applicationId ->
                        navController.navigate("application_details/$applicationId")
                    }
                )
            }

            composable("application_details/{applicationId}") {
                ApplicationDetailRoute(onDelete = {
                    navController.navigate("application_list")
                })
            }
    }
}