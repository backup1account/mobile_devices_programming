package com.example.list3.room

import androidx.annotation.WorkerThread
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


class TaskListRepository(private val tasklistDao: TaskListDAO) {
    val allTasklists: Flow<List<TaskList>> = tasklistDao.getAll()

    @WorkerThread
    fun insertList(item: TaskList) {
        tasklistDao.insertItem(item)
    }

    @WorkerThread
    fun insertListOfItems(list: List<TaskList>) {
        tasklistDao.insertListOfItems(list)
    }

//    @WorkerThread
//    fun addImage(item: TaskList, path: String) {
//        tasklistDao.addImage(item, path)
//    }

    @WorkerThread
    fun deleteItem(item: TaskList) {
        tasklistDao.deleteItem(item)
    }

    @WorkerThread
    fun deleteAllItems() {
        tasklistDao.deleteAllItems()
    }
}