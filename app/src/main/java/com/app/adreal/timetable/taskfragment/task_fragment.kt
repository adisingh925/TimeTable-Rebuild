package com.app.adreal.timetable.taskfragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.adreal.timetable.R
import com.app.adreal.timetable.databinding.FragmentTaskFragmentBinding
import com.app.adreal.timetable.daysadapter.daysAdapter
import com.app.adreal.timetable.daysadapter.taskAdapter
import com.app.adreal.timetable.daysdatabase.daysviewmodel.daysViewModel
import com.app.adreal.timetable.daysdatabase.model.dayModel
import com.app.adreal.timetable.daysdatabase.model.taskModel
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class task_fragment : Fragment() {

    lateinit var binding: FragmentTaskFragmentBinding

    lateinit var daysViewModel: daysViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTaskFragmentBinding.inflate(layoutInflater)

        daysViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.daysdatabase.daysviewmodel.daysViewModel::class.java)

        binding.fab.setOnClickListener()
        {
            showDialog()
        }

        val adapter = this.context?.let { taskAdapter(it) }
        val recyclerview = binding.recyclerview
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        daysViewModel.readAllTasks.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            data ->
            adapter?.setdata(data)
        })

        return binding.root
    }


    private fun showDialog() {
        val dialog = Dialog(this.requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.task_dialog)
        dialog.findViewById<Button>(R.id.cancel).setOnClickListener()
        {
            dialog.dismiss()
        }
        dialog.findViewById<EditText>(R.id.time).setOnClickListener()
        {
            timePickerDialog(dialog.findViewById(R.id.time))
        }
        dialog.findViewById<EditText>(R.id.date).setOnClickListener()
        {
            datePicker(dialog.findViewById(R.id.date))
        }
        dialog.findViewById<Button>(R.id.done).setOnClickListener()
        {
            if(!dialog.findViewById<EditText>(R.id.title).text.isNullOrEmpty() && !dialog.findViewById<EditText>(R.id.date).text.isNullOrEmpty() && !dialog.findViewById<EditText>(R.id.time).text.isNullOrEmpty() && !dialog.findViewById<EditText>(R.id.info).text.isNullOrEmpty())
            {
                val data = taskModel(0,dialog.findViewById<EditText>(R.id.title).text.toString(),dialog.findViewById<EditText>(R.id.info).text.toString(),dialog.findViewById<EditText>(R.id.time).text.toString(),dialog.findViewById<EditText>(R.id.date).text.toString())
                daysViewModel.insertTask(data)
                dialog.dismiss()
            }
            else
            {
                Toast.makeText(this.context,"please enter value for all fields", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    private fun timePickerDialog(edittext : EditText)
    {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener{ _, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY,hour)
            cal.set(Calendar.MINUTE, minute)
            val myFormat = "hh:mm aa"                                               // required format
            val stf = SimpleDateFormat(myFormat, Locale.US)
            edittext.setText(stf.format(cal.time))
        }
        TimePickerDialog(this.context,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),false).show()
    }

    private fun datePicker(edittext : EditText)
    {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this.requireContext(), { _, year, _, _ ->
            val myFormat = "dd.MM.yyyy"                                             // required format
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            edittext.setText(sdf.format(c.time))
        }, year, month, day)

        dpd.show()
    }
}