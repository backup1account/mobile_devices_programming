package com.example.list3

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.list3.dialogs.CreateItemDialogFragment
import com.example.list3.dialogs.EditItemDialogFragment
import com.example.list3.factory.ExercisesViewModelFactory
import com.example.list3.interfaces.OnButtonClickListener
import com.example.list3.room.Exercises
import com.google.android.material.floatingactionbutton.FloatingActionButton


private const val ARG_ID = "list_number"

class ExercisesFragment : Fragment(), OnButtonClickListener {
    private var columnCount = 1
    private val myAdapter = ExercisesRecyclerViewAdapter(this)

    private var param1: Int = 0

    private val exercisesViewModel: ExercisesViewModel by viewModels {
        ExercisesViewModelFactory((activity?.application as ApplicationMain).exercisesRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            param1 = it.getInt(ARG_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_exercises_list, container, false)
        val rView: RecyclerView = view.findViewById(R.id.list)

        with(rView) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = myAdapter
        }

        view.findViewById<FloatingActionButton>(R.id.add_item_fab).setOnClickListener {
            CreateItemDialogFragment(this, param1).show(
                childFragmentManager, CreateItemDialogFragment.TAG
            )
        }

        exercisesViewModel.allListExercises(param1).observe(viewLifecycleOwner) { exercises ->
            exercises?.let { myAdapter.submitList(it) }
        }
        return view
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }

    override fun onUpdateButtonClick(uid: Int) {
        EditItemDialogFragment(this, uid).show(
            childFragmentManager, EditItemDialogFragment.TAG
        )
    }

    override fun onDeleteButtonClick(item: Exercises) {
        Thread {
            exercisesViewModel.deleteExercise(item)
        }.start()
    }

    override fun onCreateDialogClickListener(item: Exercises) {
        Thread {
            exercisesViewModel.insertExercise(item)
        }.start()
    }

    override fun onSubmitDialogClickListener(title: String, description: String, uid: Int) {
        Thread {
            exercisesViewModel.updateExercise(title, description, uid)
        }.start()
    }
}