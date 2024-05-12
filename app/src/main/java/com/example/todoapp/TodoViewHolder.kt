package com.example.todoapp

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolistappication.R

class TodoViewHolder(view:View):ViewHolder(view) {

    val cbTodo:CheckBox = view.findViewById(R.id.cbTodo)
    val ivDelete:ImageView = view.findViewById(R.id.ivDelete)
    val ivEdit:ImageView = view.findViewById(R.id.ivEdit)



}