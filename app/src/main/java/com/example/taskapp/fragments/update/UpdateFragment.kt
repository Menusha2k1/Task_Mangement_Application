package com.example.taskapp.fragments.update

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.taskapp.MainActivity
import com.example.taskapp.R
import com.example.taskapp.models.Task
import com.example.taskapp.viewmodel.UserViewModel

class UpdateFragment : Fragment() {

    lateinit var updateTitle: EditText
    lateinit var updateDescription: EditText
    lateinit var updatePriority: Spinner
    lateinit var updateButton: Button
    private lateinit var mUserViewModel: UserViewModel


    private var task: Task? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        updateTitle = view.findViewById(R.id.updateFirstName_et)
        updateDescription = view.findViewById(R.id.updateLastName_et)
        updatePriority = view.findViewById(R.id.updateAge_et)
        updateButton = view.findViewById(R.id.update_btn)

        // Retrieve the User object from the arguments
        task = arguments?.getSerializable("user") as Task?

        val priorities = arrayOf("Low", "Medium", "High") // Example priorities
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, priorities)
        updatePriority.adapter = adapter

        // Populate the EditText fields with the current values of the User
        updateTitle.setText(task?.title)
        updateDescription.setText(task?.description)
        updatePriority.setSelection(priorities.indexOf(task?.priority))

        updateButton.setOnClickListener {
            updateUser()
            val intent = Intent(requireContext(), MainActivity::class.java)
            Toast.makeText(requireContext(), "Successfully Updated!!!", Toast.LENGTH_LONG).show()
            startActivity(intent)

        }


        return view
    }

    private fun updateUser() {
        val title = updateTitle.text.toString()
        val description = updateDescription.text.toString()
        val priority = updatePriority.selectedItem.toString()

        if (task != null) {
            // Update the User object with the new values
            task?.title = title
            task?.description = description
            task?.priority = priority

            // Update the user in the database
            mUserViewModel.updateUser(task!!)
        }
    }

    }

