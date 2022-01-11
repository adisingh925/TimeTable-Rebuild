package com.app.adreal.timetable.daysfragments.profile

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
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
import com.app.adreal.timetable.databinding.FragmentProfileBinding
import com.app.adreal.timetable.userdatabase.userViewModel
import com.app.adreal.timetable.userdatabase.user_model
import android.content.Intent
import android.content.RestrictionEntry.TYPE_NULL
import android.provider.MediaStore
import android.text.Editable
import android.text.method.TextKeyListener
import android.view.inputmethod.InputMethodManager

import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class profile : Fragment() {

    lateinit var binding: FragmentProfileBinding

    lateinit var userViewModel: userViewModel

    lateinit var profileViewModel: profileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)

        userViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.userdatabase.userViewModel::class.java)

        profileViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.daysfragments.profile.profileViewModel::class.java)

        updatedata()

        binding.fab.setOnClickListener()
        {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        }

        binding.edit.setOnClickListener()
        {
            findNavController().navigate(R.id.action_profile_to_editprofile)
        }

        return binding.root
    }

    fun updatedata()
    {
        userViewModel.readdata.observe(viewLifecycleOwner, Observer { data ->
            if(data!=null)
            {
                if(data.name.isNullOrEmpty()) binding.name.text = null else binding.name.text = data.name.toString()
                if(data.email.isNullOrEmpty()) binding.email.text = null else binding.email.text = data.email.toString()
                if(data.number.isNullOrEmpty()) binding.phonenumber.text = null else binding.phonenumber.text = data.number.toString()
                if(data.dob.isNullOrEmpty()) binding.dob.text = null else binding.dob.text = data.dob.toString()
                if(data.photo.isNullOrEmpty())
                {
                    Glide
                        .with(this)
                        .load(R.drawable.profile)
                        .circleCrop()
                        .into(binding.imageview)
                }
                else
                {
                    profileViewModel.imageurl = data.photo.toUri()
                    Glide
                        .with(this)
                        .load(data.photo)
                        .circleCrop()
                        .into(binding.imageview)
                }
            }
        })
    }
}