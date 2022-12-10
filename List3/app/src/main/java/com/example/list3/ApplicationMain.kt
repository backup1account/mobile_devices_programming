package com.example.list3

import android.app.Application
import com.example.list3.room.AppDatabase
import com.example.list3.room.ExercisesRepository
import com.example.list3.room.TaskListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ApplicationMain : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { AppDatabase.getInstance(this, applicationScope) }
    val repository by lazy { TaskListRepository(database.tasklistDao()) }
    val exercisesRepository by lazy { ExercisesRepository(database.exerciselistDao()) }
}