package com.example.todoapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.data.tasksRepository
import com.example.todoapp.presentation.home.HomeDestination
import com.example.todoapp.presentation.home.HomeScreen
import com.example.todoapp.presentation.home.HomeViewModel
import com.example.todoapp.presentation.task.TaskEntryDestination
import com.example.todoapp.presentation.task.TaskEntryScreen
import com.example.todoapp.presentation.task.TaskEntryViewModel

@Composable
fun AppNavGraph(
    homeViewModel: HomeViewModel,
    taskEntryViewModel: TaskEntryViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeDestination.route
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToTaskEntry = { navController.navigate(TaskEntryDestination.route) },
                homeViewModel = homeViewModel,
            )
        }
        composable(route = TaskEntryDestination.route) {
            TaskEntryScreen(
                navigateToHome = { navController.popBackStack() },
                taskEntryViewModel = taskEntryViewModel
            )
        }
    }
}