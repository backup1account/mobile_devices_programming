package com.example.list3

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.list3.interfaces.OnButtonClickListener

import com.example.list3.room.Exercises


class ExercisesRecyclerViewAdapter(
    private val onButtonClickHandler: OnButtonClickListener
) : ListAdapter<Exercises, ExercisesRecyclerViewAdapter.ExerciseViewHolder>(ExercisesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item.title, item.description)

        holder.editItemBtn.setOnClickListener {
            onButtonClickHandler.onUpdateButtonClick(item.uid)
        }
        holder.deleteItemBtn.setOnClickListener {
            onButtonClickHandler.onDeleteButtonClick(item)
        }
    }

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.exercise_title)
        private val contentView: TextView = itemView.findViewById(R.id.exercise_description)

        val editItemBtn: ImageButton = itemView.findViewById(R.id.edit_item_btn)
        val deleteItemBtn: ImageButton = itemView.findViewById(R.id.delete_item_btn)

        fun bind(title: String?, description: String?) {
            titleView.text = title
            contentView.text = description
        }

        companion object {
            fun create(parent: ViewGroup): ExerciseViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_exercises, parent, false)
                return ExerciseViewHolder(view)
            }
        }
    }

    class ExercisesComparator : DiffUtil.ItemCallback<Exercises>() {
        override fun areItemsTheSame(oldItem: Exercises, newItem: Exercises): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Exercises, newItem: Exercises): Boolean {
            return oldItem.title == newItem.title
        }
    }
}