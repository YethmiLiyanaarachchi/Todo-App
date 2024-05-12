package com.example.todoapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.database.Todo
import com.example.todoapp.database.TodoDatabase
import com.example.todoapp.database.TodoRepository
import com.example.todolistappication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TodoAdapter
    private lateinit var viewModel:MainActivityData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView:RecyclerView = findViewById(R.id.rvTodoList)
        val repository=TodoRepository(TodoDatabase.getInstance(this))


        viewModel=ViewModelProvider(this)[MainActivityData::class.java]

        viewModel.data.observe(this){
            adapter=TodoAdapter(it,repository,viewModel)
            recyclerView.adapter= adapter
            recyclerView.layoutManager=LinearLayoutManager(this)


        }

        CoroutineScope(Dispatchers.IO).launch {
            val data=repository.getAllTodoItems()

            runOnUiThread{
                viewModel.setData(data)
            }
        }

        val addItem:Button=findViewById(R.id.btnadditem)

        addItem.setOnClickListener{
            displayAlert(repository)
        }

    }

    private fun displayAlert(repository:TodoRepository){
        val builder= AlertDialog.Builder  (this)

        builder.setTitle(getText(R.string.alertTitle))
        builder.setMessage("Enter the to do the item below:")

        val input = EditText(this)
        input.inputType=InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("ok"){dialog,which ->
            val item=input.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                repository.insert(Todo(item))
                val data= repository.getAllTodoItems()
                runOnUiThread{
                    viewModel.setData(data)
                }
            }
        }

        builder.setNegativeButton("cancel"){ dialog, which ->
            dialog.cancel()
        }

        //show alert

        val alertDialog=builder.create()
        alertDialog.show()

    }
}