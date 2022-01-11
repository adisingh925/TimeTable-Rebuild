package com.app.adreal.timetable.daysfragments.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.adreal.timetable.R
import com.app.adreal.timetable.databinding.FragmentEditprofileBinding
import com.app.adreal.timetable.userdatabase.userViewModel
import com.app.adreal.timetable.userdatabase.user_model
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class editprofile : Fragment() {

    lateinit var binding: FragmentEditprofileBinding

    lateinit var userViewModel: userViewModel

    lateinit var profileViewModel: profileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditprofileBinding.inflate(layoutInflater)

        userViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.userdatabase.userViewModel::class.java)

        profileViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.daysfragments.profile.profileViewModel::class.java)

        updatedata()

        binding.fab.setOnClickListener()
        {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        }

        binding.save.setOnClickListener()
        {
            if(binding.name.text.isNullOrEmpty())
            {
                Toast.makeText(requireContext(),"Name cannot be blank", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val name = if(binding.name.text.isNullOrEmpty()) null else binding.name.text.toString()
                val email = if(binding.email.text.isNullOrEmpty()) null else binding.email.text.toString()
                val phone = if(binding.phonenumber.text.isNullOrEmpty()) null else binding.phonenumber.text.toString()
                val dob = if(binding.dob.text.isNullOrEmpty()) null else binding.dob.text.toString()

                userViewModel.istableexists.observe(viewLifecycleOwner, Observer { data ->
                    if(data == true)
                    {
                        val user = user_model(1,name,email,phone,dob,profileViewModel.imageurl.toString())
                        userViewModel.update(user)
                    }
                    else
                    {
                        val user = user_model(1,name,email,phone,dob,profileViewModel.imageurl.toString())
                        userViewModel.insert(user)
                    }
                })
            }
        }

        return binding.root
    }

    fun updatedata()
    {
        userViewModel.readdata.observe(viewLifecycleOwner, Observer { data ->
            if(data!=null)
            {
                if(data.name.isNullOrEmpty()) binding.name.text = null else binding.name.setText(data.name.toString())
                if(data.email.isNullOrEmpty()) binding.email.text = null else binding.email.setText(data.email.toString())
                if(data.number.isNullOrEmpty()) binding.phonenumber.text = null else binding.phonenumber.setText(data.number.toString())
                if(data.dob.isNullOrEmpty()) binding.dob.text = null else binding.dob.setText(data.dob.toString())
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 100 && resultCode == RESULT_OK)
        {
            val imageUri = data?.data

            profileViewModel.imageurl = imageUri

            Glide
                .with(this)
                .load(imageUri)
                .circleCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageview)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}