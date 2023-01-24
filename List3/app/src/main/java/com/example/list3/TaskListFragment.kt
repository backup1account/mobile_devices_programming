package com.example.list3

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.list3.factory.TaskListViewModelFactory
import com.example.list3.interfaces.OnItemClickListener
import com.example.list3.room.TaskList


class TaskListFragment : Fragment(), OnItemClickListener {
    private var columnCount = 1
    private val myAdapter = TaskListRecyclerViewAdapter(this)

    private val listsViewModel: TaskListViewModel by viewModels {
        TaskListViewModelFactory((activity?.application as ApplicationMain).repository)
    }

    private var itemId: Int? = null

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>


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
        val view = inflater.inflate(R.layout.fragment_task_list_list, container, false)

        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val uri: Uri? = data?.data

                Thread {
                    listsViewModel.addImage(itemId!!, uri.toString())
                }.start()
            }
        }

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = myAdapter
            }
        }

        listsViewModel.allTasklists.observe(viewLifecycleOwner) { lists ->
            lists?.let { myAdapter.submitList(it) }
        }

        return view
    }

    override fun onItemClick(item: TaskList) {
        val bundle = bundleOf(
            "list_number" to item.uid
        )
        findNavController().navigate(R.id.exercisesFragment, bundle)
    }

    override fun onImageButtonClick(item: TaskList) {
        val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        itemId = item.uid
        resultLauncher.launch(i)
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}