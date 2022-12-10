package com.example.list3

import androidx.lifecycle.*
import com.example.list3.room.TaskList
import com.example.list3.room.TaskListRepository


class TaskListViewModel(private val repository: TaskListRepository) : ViewModel() {
    val allTasklists: LiveData<List<TaskList>> = repository.allTasklists.asLiveData()

    fun addImage(itemId: Int, path: String) {
        repository.addImage(itemId, path)
    }
}