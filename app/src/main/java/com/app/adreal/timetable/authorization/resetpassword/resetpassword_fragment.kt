package com.app.adreal.timetable.authorization.resetpassword

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.adreal.timetable.R
import com.app.adreal.timetable.databinding.FragmentResetpasswordFragmentBinding


class resetpassword_fragment : Fragment() {

    lateinit var binding: FragmentResetpasswordFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResetpasswordFragmentBinding.inflate(layoutInflater)

        return binding.root
    }
}