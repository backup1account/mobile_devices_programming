package com.example.list3.room

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow


class ExercisesRepository(private val exercisesDao: ExercisesDAO) {
    @WorkerThread
    fun allListExercises(listNumber: Int) : Flow<List<Exercises>> {
        return exercisesDao.getListExercises(listNumber)
    }

    @WorkerThread
    fun insertExercise(item: Exercises) {
        exercisesDao.insertExercise(item)
    }

    @WorkerThread
    fun updateExercise(title: String, description: String, uid: Int) {
        exercisesDao.updateExercise(title, description, uid)
    }

    @WorkerThread
    fun deleteExercise(item: Exercises) {
        exercisesDao.deleteExercise(item)
    }
}