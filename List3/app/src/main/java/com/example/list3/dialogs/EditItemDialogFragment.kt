package com.example.list3.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.list3.R
import com.example.list3.interfaces.OnButtonClickListener


class EditItemDialogFragment(
    private val onDialogClickHandler: OnButtonClickListener,
    private val itemId: Int
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?) : Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.edit_item_dialog, null)

            val newTitle: EditText by lazy { view.findViewById(R.id.edit_exercise_title) }
            val newDescription: EditText by lazy { view.findViewById(R.id.edit_exercise_description) }

            builder.setView(view)
                .setPositiveButton("Confirm") { _, _ ->
                    onDialogClickHandler.onSubmitDialogClickListener(
                        newTitle.text.toString(),
                        newDescription.text.toString(),
                        itemId
                    )
                }
                .setNegativeButton("Cancel") { _, _ ->
                    dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        const val TAG = "EditItemDialog"
    }
}