package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.data.tasksRepository
import com.example.todoapp.presentation.navigation.AppNavGraph
import com.example.todoapp.presentation.home.HomeViewModel
import com.example.todoapp.presentation.home.HomeViewModelFactory
import com.example.todoapp.presentation.task.TaskEntryViewModel
import com.example.todoapp.presentation.task.TaskEntryViewModelFactory
import com.example.todoapp.presentation.theme.TodoAppTheme

class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this, HomeViewModelFactory(tasksRepository))
            .get(HomeViewModel::class.java)
    }

    private val taskEntryViewModel: TaskEntryViewModel by lazy {
        ViewModelProvider(this, TaskEntryViewModelFactory(tasksRepository))
            .get(TaskEntryViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                AppNavGraph(
                    homeViewModel = homeViewModel,
                    taskEntryViewModel = taskEntryViewModel
                )
            }
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoAppTheme {
        HomeScreen()
    }
}*/
