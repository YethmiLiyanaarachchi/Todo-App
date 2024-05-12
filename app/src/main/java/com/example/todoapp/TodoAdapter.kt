package com.example.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter

class TodoAdapter:Adapter<TodoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item,parent,false)

        return TodoViewHolder(view)

    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.cbTodo.text = "Sample Text"
    }

}