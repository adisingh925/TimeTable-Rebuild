package com.app.adreal.timetable.daysfragments

import android.app.Dialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.adreal.timetable.R
import com.app.adreal.timetable.databinding.FragmentThursdayBinding
import com.app.adreal.timetable.daysadapter.thursdayadapter
import com.app.adreal.timetable.daysdatabase.daysviewmodel.thursdayViewModel
import com.app.adreal.timetable.daysdatabase.model.thursday_model
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*


class thursday : Fragment(), thursdayadapter.OnItemClickListener {

    lateinit var binding: FragmentThursdayBinding

    lateinit var thursdayViewModel: thursdayViewModel

    private var auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThursdayBinding.inflate(layoutInflater)

        thursdayViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.daysdatabase.daysviewmodel.thursdayViewModel::class.java)

        val adapter = thursdayadapter(this)
        val recyclerview = binding.recyclerview
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        binding.fab.setOnClickListener()
        { showcustomdialog() }

        thursdayViewModel.readalldata.observe(viewLifecycleOwner, androidx.lifecycle.Observer { data ->
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
                val data = thursday_model(0,auth.uid.toString(),dialog.findViewById<EditText>(R.id.starttime).text.toString(),dialog.findViewById<EditText>(R.id.endtime).text.toString(),dialog.findViewById<EditText>(R.id.subject).text.toString())
                thursdayViewModel.insert(data)
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

    override fun onItemClick(data: thursday_model) {
        thursdayViewModel.delete(data)
    }
}