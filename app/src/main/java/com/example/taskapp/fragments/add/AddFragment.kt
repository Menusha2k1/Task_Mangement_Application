package com.example.taskapp.fragments.add

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.taskapp.MainActivity
import com.example.taskapp.R
import com.example.taskapp.models.Task
import com.example.taskapp.viewmodel.UserViewModel

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var spn:Spinner

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        firstNameEditText = view.findViewById(R.id.addFirstName_et)
        lastNameEditText = view.findViewById(R.id.addLastName_et)
        spn = view.findViewById(R.id.spnPriority)


        val AddBTN = view.findViewById<Button>(R.id.add_btn)
        AddBTN.setOnClickListener{
            insertDataToDatabase()
        }
        return view
    }


    private fun insertDataToDatabase(){
        val title = firstNameEditText.text.toString()
        val description = lastNameEditText.text.toString()
        val priority = spn.selectedItem.toString()
        val priority2 = spn


        if(inputCheck(title,description,priority)){
            // Create User Object
            val task = Task(0, title, description,priority)





            // Add Data To Database
            mUserViewModel.addTask(task)
            Toast.makeText(requireContext(),"Successfully added!" , Toast.LENGTH_LONG).show()
            //navigate back
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(requireContext(),"Please Fill the fields." , Toast.LENGTH_LONG).show()
        }

    }
    fun inputCheck(title: String, description: String, priority: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description) && priority.isEmpty())

    }


}