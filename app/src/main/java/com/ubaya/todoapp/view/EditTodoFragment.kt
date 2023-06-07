package com.ubaya.todoapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.todoapp.R
import com.ubaya.todoapp.databinding.FragmentEditTodoBinding
import com.ubaya.todoapp.model.Todo
import com.ubaya.todoapp.viewmodel.DetailTodoListViewModel
import com.ubaya.todoapp.viewmodel.ListTodoViewModel
import org.w3c.dom.Text

class EditTodoFragment : Fragment(), FragmentEditTodoLayoutInterface {
    private lateinit var viewModel: DetailTodoListViewModel
    private lateinit var dataBinding:FragmentEditTodoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        dataBinding.radiolistener = this
        dataBinding.savelistener = this

        viewModel = ViewModelProvider(this).get(DetailTodoListViewModel::class.java)
//        val txtJudulTodo = view.findViewById<TextView>(R.id.txtJudulTodo)
//        txtJudulTodo.text = "Edit Todo"
//        val btnEdit = view.findViewById<Button>(R.id.btnAdd)
//        btnEdit.text = "Save Changes"
//
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

//        btnEdit.setOnClickListener {
//            var txtTitle = view.findViewById<EditText>(R.id.txtTitle)
//            var txtNote = view.findViewById<EditText>(R.id.txtNotes)
//            val radioGroupPriority = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
//            val radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
//
//            viewModel.update(txtTitle.text.toString(), txtNote.text.toString(),
//                radio.tag.toString().toInt(), 0, uuid)
//            Toast.makeText(context, "Todo updated", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//        }
        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditTodoBinding>(inflater,
            R.layout.fragment_edit_todo, container, false)
        return dataBinding.root
//        return inflater.inflate(R.layout.fragment_edit_todo, container, false)
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
            /*val txtTitle = view?.findViewById<EditText>(R.id.txtTitle)
            val txtNotes = view?.findViewById<EditText>(R.id.txtNotes)

            txtTitle?.setText(it.title)
            txtNotes?.setText(it.notes)
            val radioLow = view?.findViewById<RadioButton>(R.id.radioLow)
            val radioMedium = view?.findViewById<RadioButton>(R.id.radioMedium)
            val radioHigh = view?.findViewById<RadioButton>(R.id.radioHigh)

            when(it.priority){
                1 -> radioLow?.isChecked = true
                2 -> radioMedium?.isChecked = true
                3 -> radioHigh?.isChecked = true
            }*/
        })
    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onTodoSave(v: View, obj: Todo) {
        Log.d("Tes", obj.toString())
        viewModel.update(obj.title, obj.notes, obj.priority, obj.is_done, obj.uuid)
        Toast.makeText(context, "Todo updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }

}