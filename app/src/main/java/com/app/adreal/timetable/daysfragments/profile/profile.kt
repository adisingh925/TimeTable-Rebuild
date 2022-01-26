package com.app.adreal.timetable.daysfragments.profile

import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.adreal.timetable.R
import com.app.adreal.timetable.databinding.FragmentProfileBinding
import com.app.adreal.timetable.userdatabase.userViewModel
import com.app.adreal.timetable.userdatabase.user_model
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton


class profile : Fragment() {

    lateinit var binding: FragmentProfileBinding

    lateinit var userViewModel: userViewModel

    lateinit var profileViewModel: profileViewModel

    lateinit var dialog : Dialog

    //private var auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)

        userViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.userdatabase.userViewModel::class.java)

        profileViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.daysfragments.profile.profileViewModel::class.java)

        updatedata()

//        binding.fab.setOnClickListener()
//        {
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.type = "image/*"
//            startActivityForResult(intent, 100)
//        }

        binding.edit.setOnClickListener()
        {
//            if(!profileViewModel.fabstate)
//            {
//                if(binding.name.text.isNullOrEmpty())
//                {
//                    Toast.makeText(requireContext(),"Name cannot be blank", Toast.LENGTH_SHORT).show()
//                }
//                else
//                {
//                    val name = if(binding.name.text.isNullOrEmpty()) null else binding.name.text.toString()
//                    val email = if(binding.email.text.isNullOrEmpty()) null else binding.email.text.toString()
//                    val phone = if(binding.phonenumber.text.isNullOrEmpty()) null else binding.phonenumber.text.toString()
//                    val dob = if(binding.dob.text.isNullOrEmpty()) null else binding.dob.text.toString()
//
//                    userViewModel.istableexists.observe(viewLifecycleOwner, Observer { data ->
//                        if(data == true)
//                        {
//                            val user = user_model(auth.uid.toString(),name,email,phone,dob,profileViewModel.imageurl.toString())
//                            userViewModel.update(user)
//                        }
//                        else
//                        {
//                            val user = user_model(auth.uid.toString(),name,email,phone,dob,profileViewModel.imageurl.toString())
//                            userViewModel.insert(user)
//                        }
//                    })
//                }
//            }
//            else
//            {
//                profileViewModel.fabstate = false
//            }
            dialog = Dialog(this.requireContext())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setContentView(R.layout.editprofile_dialog)
            dialog.findViewById<ImageView>(R.id.profileimage).setOnClickListener()
            {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, 100)
            }
            dialog.findViewById<FloatingActionButton>(R.id.edit).setOnClickListener()
            {
                setdata()
            }
            setdatatodialog()
            dialog.show()
        }

        return binding.root
    }

    private fun setdata() {
        if(dialog.findViewById<EditText>(R.id.name).text.isNullOrEmpty())
        {
            Toast.makeText(requireContext(),"Name cannot be blank", Toast.LENGTH_SHORT).show()
        }
        else
        {
            val name = if(dialog.findViewById<EditText>(R.id.name).text.isNullOrEmpty()) null else dialog.findViewById<EditText>(R.id.name).text.toString()
            val email = if(dialog.findViewById<EditText>(R.id.email).text.isNullOrEmpty()) null else dialog.findViewById<EditText>(R.id.email).text.toString()
            val phone = if(dialog.findViewById<EditText>(R.id.phonenumber).text.isNullOrEmpty()) null else dialog.findViewById<EditText>(R.id.phonenumber).text.toString()
            val dob = if(dialog.findViewById<EditText>(R.id.dob).text.isNullOrEmpty()) null else dialog.findViewById<EditText>(R.id.dob).text.toString()

            userViewModel.istableexists.observe(viewLifecycleOwner, Observer { data ->
                if(data == true)
                {
                    val user = user_model("aditya",name,email,phone,dob,profileViewModel.imageurl.toString())
                    userViewModel.update(user)
                }
                else
                {
                    val user = user_model("aditya",name,email,phone,dob,profileViewModel.imageurl.toString())
                    userViewModel.insert(user)
                }
            })
            dialog.dismiss()
        }
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
                if(data.photo.isNullOrEmpty() || data.photo == "null")
                {
                    Glide
                        .with(this)
                        .load(R.drawable.profile)
                        .circleCrop()
                        .dontTransform()
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
            else
            {
                Glide
                    .with(this)
                    .load(R.drawable.profile)
                    .circleCrop()
                    .dontTransform()
                    .into(binding.imageview)
            }
        })
    }

    fun setdatatodialog()
    {
        userViewModel.readdata.observe(viewLifecycleOwner, Observer { data ->
            if(data!=null)
            {
                if(data.name.isNullOrEmpty()) dialog.findViewById<EditText>(R.id.name).text = null else dialog.findViewById<EditText>(R.id.name).setText(data.name.toString())
                if(data.email.isNullOrEmpty()) dialog.findViewById<EditText>(R.id.email).text = null else dialog.findViewById<EditText>(R.id.email).setText(data.email.toString())
                if(data.number.isNullOrEmpty()) dialog.findViewById<EditText>(R.id.phonenumber).text = null else dialog.findViewById<EditText>(R.id.phonenumber).setText(data.number.toString())
                if(data.dob.isNullOrEmpty()) dialog.findViewById<EditText>(R.id.dob).text = null else dialog.findViewById<EditText>(R.id.dob).setText(data.dob.toString())
                if(data.photo == "null" || data.photo.isNullOrEmpty())
                {
                    Glide
                        .with(this)
                        .load(R.drawable.profile)
                        .circleCrop()
                        .dontTransform()
                        .into(dialog.findViewById(R.id.profileimage))
                }
                else
                {
                    profileViewModel.imageurl = data.photo.toUri()
                    Glide
                        .with(this)
                        .load(data.photo)
                        .circleCrop()
                        .into(dialog.findViewById(R.id.profileimage))
                }
            }
            else
            {
                Glide
                    .with(this)
                    .load(R.drawable.profile)
                    .circleCrop()
                    .dontTransform()
                    .into(dialog.findViewById(R.id.profileimage))
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
                .into(dialog.findViewById(R.id.profileimage))

            Glide
                .with(this)
                .load(imageUri)
                .circleCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageview)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun showcustomdialog()
    {

    }
}