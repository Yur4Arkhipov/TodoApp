package com.example.todoapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todoapp.presentation.home.HomeDestination
import com.example.todoapp.presentation.home.HomeScreen
import com.example.todoapp.presentation.task.TaskDetailsDestination
import com.example.todoapp.presentation.task.TaskDetailsScreen
import com.example.todoapp.presentation.task.TaskEntryDestination
import com.example.todoapp.presentation.task.TaskEntryScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToTaskEntry = { navController.navigate(TaskEntryDestination.route) },
                navigateToTaskDetails = { navController.navigate("${TaskDetailsDestination.route}/$it") },
            )
        }
        composable(route = TaskEntryDestination.route) {
            TaskEntryScreen(
                navigateToHome = { navController.popBackStack() },
            )
        }
        composable(
            route = TaskDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(TaskDetailsDestination.taskIdArg) { type = NavType.IntType })
        ) {
            TaskDetailsScreen(
                navigateToHome = { navController.popBackStack() },
            )
        }
    }
}