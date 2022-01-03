package com.app.adreal.timetable.authorization.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.adreal.timetable.R
import com.app.adreal.timetable.authorization.viewmodel.authViewModel
import com.app.adreal.timetable.constants.constants.Companion.clientID
import com.app.adreal.timetable.constants.constants.Companion.request_code
import com.app.adreal.timetable.databinding.FragmentLoginFragmentBinding
import com.app.adreal.timetable.homeactivity.homeactivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class login_fragment : Fragment() {

    lateinit var binding: FragmentLoginFragmentBinding

    lateinit var authViewModel: authViewModel

    var user = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if(user.currentUser!=null)
        {
            //go to home activity
            val intent = Intent(this.context,homeactivity::class.java)
            startActivity(intent)

            activity?.finish()
        }

        // Inflate the layout for this fragment
        binding = FragmentLoginFragmentBinding.inflate(layoutInflater)

        authViewModel = ViewModelProvider(this).get(com.app.adreal.timetable.authorization.viewmodel.authViewModel::class.java)

        binding.registerButton.setOnClickListener()
        {
            findNavController().navigate(R.id.action_login_fragment_to_register_fragment)
        }

        binding.forgetText.setOnClickListener()
        {
            findNavController().navigate(R.id.action_login_fragment_to_resetpassword_fragment)
        }

        binding.loginButton.setOnClickListener()
        {
            if(binding.email.text.isNotEmpty() and binding.password.text.isNotEmpty())
            {
                authViewModel.firebase_signin(binding.email.text.toString(),binding.password.text.toString())
                binding.google.isVisible = false
                binding.loginButton.isVisible = false
                binding.registerButton.isVisible = false
                binding.loadingAnimation.isVisible = true
                binding.loadingAnimation.playAnimation()
            }
            else{
                Toast.makeText(this.context,"Please enter value for all Fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.google.setOnClickListener()
        {
            googlesignin()
        }

        authViewModel.loginstate.observe(viewLifecycleOwner, Observer { value ->
            if(value == true)
            {
                binding.google.isVisible = true
                binding.loginButton.isVisible = true
                binding.registerButton.isVisible = true
                binding.loadingAnimation.isVisible = false
                binding.loadingAnimation.cancelAnimation()
                Toast.makeText(this.context,"Login Successful", Toast.LENGTH_SHORT).show()

                //go to home activity
                val intent = Intent(this.context, homeactivity::class.java)
                startActivity(intent)

                activity?.finish()
            }
            else
            {
                binding.google.isVisible = true
                binding.loginButton.isVisible = true
                binding.registerButton.isVisible = true
                binding.loadingAnimation.isVisible = false
                binding.loadingAnimation.cancelAnimation()
                Toast.makeText(this.context,"Login Failed", Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

    fun googlesignin()
    {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientID)
            .requestEmail()
            .requestProfile()
            .build()

        val googlesigninclient = GoogleSignIn.getClient(requireActivity(),gso)

        val signinintent = googlesigninclient.signInIntent
        startActivityForResult(signinintent,request_code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == request_code)
        {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d("google auth", "firebaseAuthWithGoogle:" + account.id)
                authViewModel.firebaseAuthWithGoogle(account.idToken!!)

                //go to home activity
                val intent = Intent(this.context,homeactivity::class.java)
                startActivity(intent)

                activity?.finish()
            }
            catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("google auth", "Google sign in failed", e)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}