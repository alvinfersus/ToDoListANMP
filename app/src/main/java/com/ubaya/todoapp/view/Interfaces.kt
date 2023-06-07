package com.ubaya.todoapp.view

import android.view.View
import android.webkit.WebSettings.RenderPriority
import android.widget.CompoundButton
import com.ubaya.todoapp.model.Todo

interface TodoItemLayoutInterface{
    fun onCheckedChange(cb:CompoundButton, isChecked:Boolean, obj:Todo)
    fun onTodoEditClick(v:View)
}

interface FragmentEditTodoLayoutInterface{
    fun onRadioClick(v:View, priority:Int, obj:Todo)
    fun onTodoSave(v:View, obj: Todo)
}