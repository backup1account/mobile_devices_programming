package com.example.list2

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.list2.placeholder.PlaceholderContent


class ListFragment : Fragment(), OnClickHandler {
    private var columnCount = 1
    private var myAdapter: ItemRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        myAdapter = context?.let {
            ItemRecyclerViewAdapter(it.applicationContext, PlaceholderContent.ITEMS, this)
        }

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = myAdapter
            }
        }
        return view
    }

    override fun onClickItem(crimeItem: PlaceholderContent.CrimeItem) {
        val bundle = bundleOf(
            "title" to crimeItem.title,
            "description" to crimeItem.description,
            "index" to crimeItem.index,
            "is_solved" to crimeItem.isSolved
        )
        findNavController().navigate(R.id.detailFragment, bundle)
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}