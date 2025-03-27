package com.example.todoapp.presentation.task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.Task
import com.example.todoapp.data.TasksRepository

data class TaskUiState(
    val taskDetails: TaskDetails = TaskDetails(),
    val isEntryValid: Boolean = false
)

data class TaskDetails(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
)

class TaskEntryViewModel(private val repository: TasksRepository) : ViewModel() {

    var taskUiState by mutableStateOf(TaskUiState())
        private set

    fun updateUiState(taskDetails: TaskDetails) {
        taskUiState =
            TaskUiState(taskDetails = taskDetails, isEntryValid = validateInput(taskDetails))
    }

    private fun validateInput(uiState: TaskDetails = taskUiState.taskDetails): Boolean {
        return with(uiState) {
            title.isNotBlank() && description.isNotBlank()
        }
    }

    suspend fun saveItem() {
        if (validateInput()) {
            repository.insert(taskUiState.taskDetails.toTask())
        }
    }
}

fun TaskDetails.toTask(): Task = Task(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted
)

fun Task.toTaskDetails(): TaskDetails = TaskDetails(
    id = id,
    title = title ?: "No info description",
    description = description.toString(),
)