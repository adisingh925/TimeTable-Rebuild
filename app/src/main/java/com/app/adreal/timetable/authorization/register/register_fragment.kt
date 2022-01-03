package com.app.adreal.timetable.authorization.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.adreal.timetable.R
import com.app.adreal.timetable.authorization.viewmodel.authViewModel
import com.app.adreal.timetable.databinding.FragmentRegisterFragmentBinding
import com.app.adreal.timetable.homeactivity.homeactivity


class register_fragment : Fragment() {

    lateinit var binding: FragmentRegisterFragmentBinding

    lateinit var authViewModel: authViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterFragmentBinding.inflate(layoutInflater)

        authViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.authorization.viewmodel.authViewModel::class.java)

        binding.registerButton.setOnClickListener()
        {
            if(binding.name.text.isNotEmpty() and binding.email.text.isNotEmpty() and binding.password.text.isNotEmpty())
            {
                authViewModel.firebase_register(binding.email.text.toString(),binding.password.text.toString())
                binding.registerButton.isVisible = false
                binding.loadingAnimation.isVisible = true
                binding.loadingAnimation.playAnimation()
            }
            else
            {
                Toast.makeText(this.context,"Please enter value for all Fields",Toast.LENGTH_SHORT).show()
            }
        }

        authViewModel.registerstate.observe(viewLifecycleOwner, Observer { value ->
            if(value == true)
            {
                binding.loadingAnimation.isVisible = false
                binding.registerButton.isVisible = true
                binding.loadingAnimation.cancelAnimation()
                Toast.makeText(this.context,"Registration Successful",Toast.LENGTH_SHORT).show()

                //go to home activity
                val intent = Intent(this.context, homeactivity::class.java)
                startActivity(intent)

                activity?.finish()
            }
            else
            {
                binding.loadingAnimation.isVisible = false
                binding.registerButton.isVisible = true
                binding.loadingAnimation.cancelAnimation()
                Toast.makeText(this.context,"Registration Failed",Toast.LENGTH_SHORT).show()
            }
        })
        return binding.root
    }

}