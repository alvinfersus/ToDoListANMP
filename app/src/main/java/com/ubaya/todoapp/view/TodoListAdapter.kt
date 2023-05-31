package com.ubaya.todoapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.todoapp.R
import com.ubaya.todoapp.model.Todo

class TodoListAdapter(val todos:ArrayList<Todo>, val todoOnClick:(Todo) -> Unit):RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(var view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var checkTask = holder.view.findViewById<CheckBox>(R.id.checkTask)
        checkTask.text = todos[position].title
        checkTask.isChecked = false

        checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                Log.d("hapus", "hapus")
                todoOnClick(todos[position])
            }
//            todoOnClick(todos[position])
        }

        val imgEdit = holder.view.findViewById<ImageView>(R.id.imgEdit)
        imgEdit.setOnClickListener{
            val action = TodoListFragmentDirections.actionEditTodoFragment(todos[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun updateTodoList(newtodos:List<Todo>){
        todos.clear()
        todos.addAll(newtodos)
        notifyDataSetChanged()
    }
}