package com.example.list3.interfaces

import com.example.list3.room.Exercises

interface OnButtonClickListener {
    fun onUpdateButtonClick(uid: Int)
    fun onDeleteButtonClick(item: Exercises)

    fun onCreateDialogClickListener(item: Exercises)
    fun onSubmitDialogClickListener(title: String, description: String, uid: Int)
}