package com.example.list3.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ExercisesDAO {
    @Query("SELECT * FROM exercises_table WHERE list_number LIKE :id")
    fun getListExercises(id: Int): Flow<List<Exercises>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertExercise(item: Exercises)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    fun insertListOfExercises(list: List<Exercises>)

    @Query("UPDATE exercises_table SET title = :title, description = :description WHERE uid = :uid")
    fun updateExercise(title: String, description: String, uid: Int)

    @Delete
    fun deleteExercise(item: Exercises)

    @Query("DELETE FROM exercises_table")
    fun deleteAllExercises()
}