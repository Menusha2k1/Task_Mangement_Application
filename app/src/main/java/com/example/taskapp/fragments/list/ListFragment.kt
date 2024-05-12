package com.example.taskapp.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R
import com.example.taskapp.viewmodel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Pass the UserViewModel to the ListAdapter
        val adapter = ListAdapter(mUserViewModel)
        // Use findViewById to get the RecyclerView reference from the inflated view
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        val fab = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        val deleteAllButton: Button = view.findViewById(R.id.delete_all_button)
        deleteAllButton.setOnClickListener {
            mUserViewModel.deleteAllUsers()
            Toast.makeText(requireContext(), "All users deleted", Toast.LENGTH_SHORT).show()
        }


        return view
    }
}
