package com.example.list2

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.list2.placeholder.PlaceholderContent.CrimeItem
import com.example.list2.databinding.FragmentListBinding


class ItemRecyclerViewAdapter(
    private val context: Context,
    private val crimeList: List<CrimeItem>,
    private val clickHandler: OnClickHandler
) : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentListBinding.inflate(LayoutInflater.from(context), parent, false
            ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(crimeList[position])
    }

    override fun getItemCount(): Int = crimeList.size
    override fun getItemId(position: Int): Long = position.toLong()

    inner class ViewHolder(binding: FragmentListBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private val titleView: TextView = binding.itemTitle
        private val solvedView: ImageView = binding.isSolved

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: CrimeItem) {
            titleView.text = item.title

            if (item.isSolved) {
                solvedView.visibility = View.INVISIBLE
            }
        }

        override fun onClick(p0: View?) {
            val crime = crimeList[bindingAdapterPosition]
            clickHandler.onClickItem(crime)
        }
    }
}