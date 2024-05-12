package com.example.taskapp.fragments.list

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R
import com.example.taskapp.models.Task
import com.example.taskapp.viewmodel.UserViewModel

class ListAdapter(private val userViewModel: UserViewModel): RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    private var taskList = emptyList<Task>()

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
        return taskList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = taskList[position]
        holder.firstNameTxt.text = currentItem.title
        holder.lastNameTxt.text = currentItem.description
        holder.ageTxt.text = currentItem.priority




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
                Toast.makeText(holder.itemView.context, "Successfully removed: ${currentItem.title}", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${currentItem.title}?")
            builder.setMessage("Are you sure you want to delete ${currentItem.title}?")
            builder.create().show()
        }
        when (currentItem.priority) {
            "High" -> holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.High))
            "Medium" -> holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.Medium))
            "Low" -> holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.low))
        }


    }


    fun setData(task: List<Task>){
        this.taskList = task
        notifyDataSetChanged()
    }

}
