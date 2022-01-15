package com.app.adreal.timetable.timetable_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.adreal.timetable.R
import com.app.adreal.timetable.databinding.FragmentTimetableBinding
import com.app.adreal.timetable.daysfragments.*
import com.app.adreal.timetable.viewpageradapter.viewpageradapter


class timetable : Fragment() {

    lateinit var binding: FragmentTimetableBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTimetableBinding.inflate(layoutInflater)

        var viewpager = binding.viewpager

        val adapter = viewpageradapter(parentFragmentManager)
        adapter.addFragment(monday(), "Monday")
        adapter.addFragment(tuesday(), "Tuesday")
        adapter.addFragment(wednesday(), "Wednesday")
        adapter.addFragment(thursday(), "Thursday")
        adapter.addFragment(friday(), "Friday")
        adapter.addFragment(saturday(), "Saturday")
        viewpager.adapter = adapter
        binding.tablayout.setupWithViewPager(binding.viewpager)

        return binding.root
    }
}