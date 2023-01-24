package com.example.list4

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.example.list4.databinding.FragmentCapitalCityBinding


class CapitalCityRecyclerViewAdapter(
    private val values: List<Country>
) : RecyclerView.Adapter<CapitalCityRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentCapitalCityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.countryView.text = item.name
        holder.capitalView.text = item.capital
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentCapitalCityBinding) : RecyclerView.ViewHolder(binding.root) {
        val countryView: TextView = binding.countryName
        val capitalView: TextView = binding.countryCapital
    }
}