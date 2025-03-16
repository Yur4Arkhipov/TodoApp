package com.example.todoapp.ui.home

import androidx.lifecycle.ViewModel
import com.example.todoapp.data.Task
import com.example.todoapp.data.TasksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeUiState(
    val taskList: List<Task> = listOf()
)

class HomeViewModel(private val repository: TasksRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = HomeUiState(
            taskList = listOf(
                Task(1, "Homework", "Do math homework", false),
                Task(2, "Game", "Play basketball", false),
                Task(3, "TV", "Watch about frog", true),
                Task(4, "Homework", "Do math homework", false),
                Task(5, "Game", "Play basketball", false),
                Task(6, "TV", "Watch about frog", true),
                Task(7, "Homework", "Do math homework", false),
                Task(8, "Game", "Play basketball", false),
                Task(9, "TV", "Watch about frog", true),
                Task(10, "Homework", "Do math homework", false),
                Task(11, "Game", "Play basketball", false),
                Task(12, "TV", "Watch about frog", true),
            )
        )
    }
}