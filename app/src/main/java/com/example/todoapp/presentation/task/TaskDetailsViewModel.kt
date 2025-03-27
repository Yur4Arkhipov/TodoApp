package com.example.todoapp.presentation.task

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.TasksRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class TaskDetailsUiState(
    val taskDetails: TaskDetails = TaskDetails()
)

class TaskDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: TasksRepository
) : ViewModel() {

    private val taskId: Int = checkNotNull(savedStateHandle[TaskDetailsDestination.taskIdArg])
    val uiState: StateFlow<TaskDetailsUiState> =
        repository.getTaskStream(taskId)
            .filterNotNull()
            .map { task ->
                TaskDetailsUiState(
                    taskDetails = task.toTaskDetails()
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TaskDetailsUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    suspend fun deleteTask() {
        repository.deleteItem(uiState.value.taskDetails.toTask())
    }
}