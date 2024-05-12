package com.example.todoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter

class TodoAdapter:Adapter<TodoViewHolder>() {

    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item,parent,false)

        context = parent.context

        return TodoViewHolder(view)

    }

    override fun getItemCount(): Int {
        return 100
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.cbTodo.text = "Sample Text"
        holder.ivDelete.setOnClickListener {
            Toast.makeText(context,"Hello from Button $position",Toast.LENGTH_LONG).show()
        }
    }

}