package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.data.TasksRepository
import com.example.todoapp.data.tasksRepository
import com.example.todoapp.ui.home.HomeScreen
import com.example.todoapp.ui.home.HomeViewModel
import com.example.todoapp.ui.home.HomeViewModelFactory
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this, HomeViewModelFactory(tasksRepository))
            .get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                HomeScreen(homeViewModel)
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
