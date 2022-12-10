package com.example.list3.room

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow


class TaskListRepository(private val tasklistDao: TaskListDAO) {
    val allTasklists: Flow<List<TaskList>> = tasklistDao.getAll()

    @WorkerThread
    fun addImage(itemId: Int, path: String) {
        tasklistDao.addImage(itemId, path)
    }
}