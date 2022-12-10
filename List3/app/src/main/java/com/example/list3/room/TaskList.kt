package com.example.list3.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasklist_table")
data class TaskList(
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?
) {
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
    @ColumnInfo(name="image_path") var imgPath: String? = null
}