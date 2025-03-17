package com.example.todoapp.presentation.task

import androidx.lifecycle.ViewModel
import com.example.todoapp.data.Task
import com.example.todoapp.data.TasksRepository

class TaskEntryViewModel(private val repository: TasksRepository) : ViewModel() {

    fun addTask(title: String, description: String) {
        val newTask = Task(
            id = repository.list.size + 1,
            title = title,
            description = description,
            isCompleted = false
        )
        repository.list.add(newTask)
    }

}