package com.app.adreal.timetable.authorization.resetpassword

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.adreal.timetable.R
import com.app.adreal.timetable.authorization.viewmodel.authViewModel
import com.app.adreal.timetable.databinding.FragmentResetpasswordFragmentBinding


class resetpassword_fragment : Fragment() {

    lateinit var binding: FragmentResetpasswordFragmentBinding

    lateinit var authViewModel: authViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResetpasswordFragmentBinding.inflate(layoutInflater)

        authViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.authorization.viewmodel.authViewModel::class.java)

        binding.sendemailButton.setOnClickListener()
        {
            if(binding.email.text.isNullOrEmpty())
            {
                Toast.makeText(this.context,"Please enter an Email",Toast.LENGTH_SHORT).show()
            }
            else
            {
                authViewModel.firebase_reset_password(binding.email.text.toString())
            }
        }

        authViewModel.passwordresetstate.observe(viewLifecycleOwner, Observer {
            value ->
            if(value == true)
            {
                Toast.makeText(this.context,"Password reset link sent",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this.context,"Failed", Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }
}