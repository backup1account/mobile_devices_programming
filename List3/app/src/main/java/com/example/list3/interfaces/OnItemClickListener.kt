package com.example.list3.interfaces

import com.example.list3.room.TaskList

interface OnItemClickListener {
    fun onItemClick(item: TaskList)

    fun onImageButtonClick(item: TaskList)
}