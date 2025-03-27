package com.example.todoapp.presentation

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todoapp.TodoApplication
import com.example.todoapp.presentation.home.HomeViewModel
import com.example.todoapp.presentation.task.TaskDetailsViewModel
import com.example.todoapp.presentation.task.TaskEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(todoApplication().container.taskRepository)
        }
        // Initializer for TaskEntryViewModel
        initializer {
            TaskEntryViewModel(todoApplication().container.taskRepository)
        }
        // Initializer for TaskDetailsViewModel
        initializer {
            TaskDetailsViewModel(
                this.createSavedStateHandle(),
                todoApplication().container.taskRepository
            )
        }
    }
}

fun CreationExtras.todoApplication(): TodoApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TodoApplication)