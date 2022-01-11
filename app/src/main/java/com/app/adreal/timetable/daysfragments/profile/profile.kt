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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class profile : Fragment() {

    lateinit var binding: FragmentProfileBinding

    lateinit var userViewModel: userViewModel

    lateinit var profileViewModel: profileViewModel

    private var auth = Firebase.auth

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
            if(!profileViewModel.fabstate)
            {
                if(binding.name.text.isNullOrEmpty())
                {
                    Toast.makeText(requireContext(),"Name cannot be blank", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    profileViewModel.fabstate = true
                    binding.edit.setImageResource(R.drawable.edit)

                    binding.name.isEnabled = false
                    binding.email.isEnabled = false
                    binding.phonenumber.isEnabled = false
                    binding.dob.isEnabled = false
                    binding.fab.isEnabled = false

                    val name = if(binding.name.text.isNullOrEmpty()) null else binding.name.text.toString()
                    val email = if(binding.email.text.isNullOrEmpty()) null else binding.email.text.toString()
                    val phone = if(binding.phonenumber.text.isNullOrEmpty()) null else binding.phonenumber.text.toString()
                    val dob = if(binding.dob.text.isNullOrEmpty()) null else binding.dob.text.toString()

                    userViewModel.istableexists.observe(viewLifecycleOwner, Observer { data ->
                        if(data == true)
                        {
                            val user = user_model(auth.uid.toString(),name,email,phone,dob,profileViewModel.imageurl.toString())
                            userViewModel.update(user)
                        }
                        else
                        {
                            val user = user_model(auth.uid.toString(),name,email,phone,dob,profileViewModel.imageurl.toString())
                            userViewModel.insert(user)
                        }
                    })
                }
            }
            else
            {
                profileViewModel.fabstate = false

                binding.edit.setImageResource(R.drawable.check)

                binding.name.isEnabled = true
                binding.email.isEnabled = true
                binding.phonenumber.isEnabled = true
                binding.dob.isEnabled = true
                binding.fab.isEnabled = true
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