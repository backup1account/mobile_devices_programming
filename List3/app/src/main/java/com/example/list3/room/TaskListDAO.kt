package com.example.list3.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskListDAO {
    @Query("SELECT * FROM tasklist_table ORDER BY uid ASC")
    fun getAll(): Flow<List<TaskList>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItem(item: TaskList)

    @Query("UPDATE tasklist_table SET image_path = :path WHERE uid = :itemId")
    fun addImage(itemId: Int, path: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    fun insertListOfItems(list: List<TaskList>)

    @Delete
    fun deleteItem(item: TaskList)

    @Query("DELETE FROM tasklist_table")
    fun deleteAllItems()
}