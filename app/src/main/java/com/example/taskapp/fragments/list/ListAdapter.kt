package com.example.taskapp.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R
import com.example.taskapp.models.User
import com.example.taskapp.viewmodel.UserViewModel

class ListAdapter(private val userViewModel: UserViewModel): RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val firstNameTxt: TextView = itemView.findViewById(R.id.firstName_txt)
        val lastNameTxt: TextView = itemView.findViewById(R.id.lastName_txt)
        val ageTxt: TextView = itemView.findViewById(R.id.age_txt)
        val edtBut: ImageView = itemView.findViewById(R.id.edtBut)
        val delBut: ImageView = itemView.findViewById(R.id.delBut)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = userList[position]
        holder.firstNameTxt.text = currentItem.firstname
        holder.lastNameTxt.text = currentItem.lastName
        holder.ageTxt.text = currentItem.age.toString()

        holder.edtBut.setOnClickListener{
            val navController = Navigation.findNavController(holder.itemView)
            val bundle = Bundle()
            bundle.putSerializable("user", currentItem)
            navController.navigate(R.id.action_listFragment_to_updateFragment, bundle)
        }

        holder.delBut.setOnClickListener{
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setPositiveButton("Yes") { _, _ ->
                userViewModel.deleteUser(currentItem)
                Toast.makeText(holder.itemView.context, "Successfully removed: ${currentItem.firstname}", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${currentItem.firstname}?")
            builder.setMessage("Are you sure you want to delete ${currentItem.firstname}?")
            builder.create().show()
        }


    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}
