package com.example.todoapp.data

import android.content.Context

interface AppContainer {
    val taskRepository: TasksRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val taskRepository: TasksRepository by lazy {
        OfflineTasksRepository(TaskDatabase.getDatabase(context).taskDao())
    }
}