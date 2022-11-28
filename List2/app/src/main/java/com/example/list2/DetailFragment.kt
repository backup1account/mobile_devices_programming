package com.example.list2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


private const val ARG_TITLE = "title"
private const val ARG_DESCRIPTION = "description"
private const val ARG_INDEX = "index"
private const val ARG_SOLVED = "is_solved"

class DetailFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var param3: Int? = null
    private var param4: Boolean? = null

    private lateinit var inflatedView: View

    private lateinit var titleField: TextView
    private lateinit var descriptionField: TextView
    private lateinit var indexField: TextView
    private lateinit var isSolvedField: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_TITLE)
            param2 = it.getString(ARG_DESCRIPTION)
            param3 = it.getInt(ARG_INDEX)
            param4 = it.getBoolean(ARG_SOLVED)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inflatedView = inflater.inflate(R.layout.fragment_detail, container, false)

        titleField = inflatedView.findViewById(R.id.item_title)
        descriptionField = inflatedView.findViewById(R.id.item_description)
        indexField = inflatedView.findViewById(R.id.item_index)
        isSolvedField = inflatedView.findViewById(R.id.is_solved)

        titleField.text = param1
        descriptionField.text = param2
        indexField.text = "Numer indeksu: ${param3.toString()}"

        if (param4 == true) {
            isSolvedField.visibility = View.INVISIBLE
        }
        return inflatedView
    }
    
}