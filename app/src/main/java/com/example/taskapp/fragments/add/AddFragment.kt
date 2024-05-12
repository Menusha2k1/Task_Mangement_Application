package com.example.taskapp.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.data.User
import com.example.taskapp.data.UserViewModel

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var ageEditText: EditText

    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        firstNameEditText = view.findViewById(R.id.addFirstName_et)
        lastNameEditText = view.findViewById(R.id.addLastName_et)
        ageEditText = view.findViewById(R.id.addAge_et)

        val AddBTN = view.findViewById<Button>(R.id.add_btn)
        AddBTN.setOnClickListener{
            insertDataToDatabase()
        }
        val Fname = view.findViewById<EditText>(R.id.addFirstName_et)
        return view
    }


    private fun insertDataToDatabase(){
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val age = ageEditText.text

        if(inputCheck(firstName,lastName,age)){
            // Create User Object
            val user = User(0, firstName, lastName,Integer.parseInt(age.toString()))
            // Add Data To Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Successfully added!" , Toast.LENGTH_LONG).show()
            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please Fill the fields." , Toast.LENGTH_LONG).show()
        }

    }
    fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())

    }

}