package com.app.adreal.timetable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.adreal.timetable.databinding.FragmentHomeactivityfragmentBinding

class homeactivityfragment : Fragment() {

    lateinit var binding: FragmentHomeactivityfragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeactivityfragmentBinding.inflate(layoutInflater)

        binding.bottomnavbar.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.home ->{
                    childFragmentManager.findFragmentById(R.id.fragmentContainerView)
                        ?.findNavController()
                        ?.navigate(R.id.homefragment)
                }

                R.id.timetable ->{
                    childFragmentManager.findFragmentById(R.id.fragmentContainerView)
                        ?.findNavController()
                        ?.navigate(R.id.timetable)

                }

                R.id.tasks ->{
                    childFragmentManager.findFragmentById(R.id.fragmentContainerView)
                        ?.findNavController()
                        ?.navigate(R.id.task_fragment)
                }
            }
            true
        }

        return binding.root
    }
}