package com.example.todoapp.presentation.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.data.TasksRepository

class TaskEntryViewModelFactory(private val repository: TasksRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
/*        if (modelClass.isAssignableFrom(TaskEntryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskEntryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown TaskEntryViewModel class")*/
        return TaskEntryViewModel(repository) as T
    }
}