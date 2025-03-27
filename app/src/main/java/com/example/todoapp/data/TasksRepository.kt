package com.example.todoapp.data

import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    suspend fun insert(task: Task)

    fun getAllTasksStream(): Flow<List<Task>>

    fun getTaskStream(id: Int): Flow<Task?>

    suspend fun getTaskById(taskId: Int): Task

    suspend fun deleteItem(item: Task)
}