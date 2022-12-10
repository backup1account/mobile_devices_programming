package com.example.list3

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.list3.room.Exercises
import com.example.list3.room.ExercisesRepository


class ExercisesViewModel(private val repository: ExercisesRepository) : ViewModel() {
    fun allListExercises(listNumber: Int) : LiveData<List<Exercises>> {
        return repository.allListExercises(listNumber).asLiveData()
    }

    fun insertExercise(item: Exercises) {
        repository.insertExercise(item)
    }

    fun updateExercise(title: String, description: String, uid: Int) {
        repository.updateExercise(title, description, uid)
    }

    fun deleteExercise(item: Exercises) {
        repository.deleteExercise(item)
    }
}