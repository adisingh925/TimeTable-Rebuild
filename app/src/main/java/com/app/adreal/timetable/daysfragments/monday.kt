package com.app.adreal.timetable.daysfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.adreal.timetable.R
import com.app.adreal.timetable.databinding.FragmentMondayBinding
import com.app.adreal.timetable.daysadapter.adapter
import com.app.adreal.timetable.daysdatabase.model.day_model


class monday : Fragment() {

    lateinit var binding: FragmentMondayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMondayBinding.inflate(layoutInflater)

        var list : List<day_model>

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}