package com.example.todoapp.data

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean
)

class TasksRepository {
    var list: MutableList<Task> = mutableListOf()
}

val tasksRepository by lazy { TasksRepository() }