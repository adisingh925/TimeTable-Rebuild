package com.app.adreal.timetable.authorization.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.adreal.timetable.R
import com.app.adreal.timetable.databinding.FragmentRegisterFragmentBinding


class register_fragment : Fragment() {

    lateinit var binding: FragmentRegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

}