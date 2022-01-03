package com.app.adreal.timetable.authorization.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.adreal.timetable.R
import com.app.adreal.timetable.databinding.FragmentLoginFragmentBinding


class login_fragment : Fragment() {

    lateinit var binding: FragmentLoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginFragmentBinding.inflate(layoutInflater)

        return binding.root
    }
}