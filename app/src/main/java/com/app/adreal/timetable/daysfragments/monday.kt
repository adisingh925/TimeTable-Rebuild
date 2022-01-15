package com.app.adreal.timetable.daysfragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.adreal.timetable.R
import com.app.adreal.timetable.databinding.FragmentMondayBinding
import com.app.adreal.timetable.daysadapter.adapter
import com.app.adreal.timetable.daysdatabase.model.monday_model
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class monday : Fragment() {

    lateinit var binding: FragmentMondayBinding

    private var auth = Firebase.auth

    lateinit var list : ArrayList<monday_model>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMondayBinding.inflate(layoutInflater)

        list = ArrayList()
        list.add(monday_model(0,auth.uid.toString(),12,13,"pcom"))

        val adapter = adapter()
        val recyclerview = binding.recyclerview
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        adapter.setdata(list)

        binding.fab.setOnClickListener()
        {
            showcustomdialog()
        }

        return binding.root
    }

    private fun showcustomdialog() {
        val dialog = Dialog(this.requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.day_dialog)
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}