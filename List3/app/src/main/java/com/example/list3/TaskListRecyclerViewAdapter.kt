package com.example.list3

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.list3.interfaces.OnItemClickListener
import com.example.list3.room.TaskList


class TaskListRecyclerViewAdapter(
    private val onItemClicked: OnItemClickListener
) : ListAdapter<TaskList, TaskListRecyclerViewAdapter.ListViewHolder>(ListsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item.title, item.description)

        holder.itemView.setOnClickListener {
            onItemClicked.onItemClick(item)
        }

        holder.addImgBtn.setOnClickListener {
            onItemClicked.onImageButtonClick(item)
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.list_title)
        private val contentView: TextView = itemView.findViewById(R.id.list_description)

//        val imageView: ImageView = itemView.findViewById(R.id.submitted_img)
        val addImgBtn: Button = itemView.findViewById(R.id.submit_img_btn)

        fun bind(title: String?, description: String?) {
            titleView.text = title
            contentView.text = description
        }

        companion object {
            fun create(parent: ViewGroup): ListViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_task_list, parent, false)
                return ListViewHolder(view)
            }
        }
    }

    class ListsComparator : DiffUtil.ItemCallback<TaskList>() {
        override fun areItemsTheSame(oldItem: TaskList, newItem: TaskList): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TaskList, newItem: TaskList): Boolean {
            return oldItem.title == newItem.title
        }
    }
}