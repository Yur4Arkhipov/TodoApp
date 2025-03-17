package com.example.todoapp.data

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean
)

class TasksRepository {
    var list: MutableList<Task> = mutableListOf(
        Task(1, "Homework", "Do math homework", false),
        Task(2, "Game", "Play basketball", false),
        Task(3, "TV", "Watch about frog", true),
    )
}

val tasksRepository by lazy { TasksRepository() }