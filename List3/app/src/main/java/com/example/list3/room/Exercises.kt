package com.example.list3.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises_table")
data class Exercises(
    @ColumnInfo(name="title") var title: String?,
    @ColumnInfo(name="description") var description: String?,
    @ColumnInfo(name="list_number") val listNumber: Int?
) {
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
}
