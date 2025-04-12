package com.example.todoapp.data.repository

import com.example.todoapp.data.Task
import com.example.todoapp.data.TaskDao
import com.example.todoapp.data.TasksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class OfflineTasksRepository(private val taskDao: TaskDao) : TasksRepository {

    override suspend fun insert(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.insertTask(task)
        }
    }
    override fun getAllTasksStream(): Flow<List<Task>> = taskDao.getAllTasks()

    override fun getTaskStream(id: Int): Flow<Task?> = taskDao.getTask(id)

    override suspend fun getTaskById(taskId: Int): Task = taskDao.getTaskById(taskId)

    override suspend fun deleteItem(item: Task) = taskDao.delete(item)
}