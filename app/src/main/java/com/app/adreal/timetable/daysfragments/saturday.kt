package com.app.adreal.timetable.daysfragments

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
import com.app.adreal.timetable.databinding.FragmentMondayBinding
import com.app.adreal.timetable.databinding.FragmentSaturdayBinding
import com.app.adreal.timetable.daysadapter.mondayadapter
import com.app.adreal.timetable.daysadapter.saturdayadapter
import com.app.adreal.timetable.daysdatabase.daysviewmodel.saturdayViewModel
import com.app.adreal.timetable.daysdatabase.model.monday_model
import com.app.adreal.timetable.daysdatabase.model.saturday_model
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*


class saturday : Fragment(), saturdayadapter.OnItemClickListener {

    lateinit var binding: FragmentSaturdayBinding

    lateinit var saturdayViewModel: saturdayViewModel

    private var auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSaturdayBinding.inflate(layoutInflater)

        saturdayViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.daysdatabase.daysviewmodel.saturdayViewModel::class.java)

        val adapter = saturdayadapter(this)
        val recyclerview = binding.recyclerview
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        binding.fab.setOnClickListener()
        { showcustomdialog() }

        saturdayViewModel.readalldata.observe(viewLifecycleOwner, androidx.lifecycle.Observer { data ->
            adapter.setdata(data)
        })

        return binding.root
    }

    private fun showcustomdialog() {
        val dialog = Dialog(this.requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.day_dialog)
        dialog.findViewById<Button>(R.id.cancel).setOnClickListener()
        {
            dialog.dismiss()
        }
        dialog.findViewById<EditText>(R.id.starttime).setOnClickListener()
        {
            timepickerdialog(dialog.findViewById(R.id.starttime))
        }
        dialog.findViewById<EditText>(R.id.endtime).setOnClickListener()
        {
            timepickerdialog(dialog.findViewById(R.id.endtime))
        }
        dialog.findViewById<Button>(R.id.done).setOnClickListener()
        {
            if(!dialog.findViewById<EditText>(R.id.starttime).text.isNullOrEmpty() && !dialog.findViewById<EditText>(R.id.endtime).text.isNullOrEmpty() && !dialog.findViewById<EditText>(R.id.subject).text.isNullOrEmpty())
            {
                val data = saturday_model(0,auth.uid.toString(),dialog.findViewById<EditText>(R.id.starttime).text.toString(),dialog.findViewById<EditText>(R.id.endtime).text.toString(),dialog.findViewById<EditText>(R.id.subject).text.toString())
                saturdayViewModel.insert(data)
                dialog.dismiss()
            }
            else
            {
                Toast.makeText(this.context,"please enter value for all fields", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    private fun timepickerdialog(edittext : EditText)
    {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener{ timepicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY,hour)
            cal.set(Calendar.MINUTE, minute)
            edittext.setText(SimpleDateFormat("hh:mm:aa").format(cal.time))
        }
        TimePickerDialog(this.context,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),false).show()
    }

    override fun onItemClick(data: saturday_model) {
        saturdayViewModel.delete(data)
    }
}