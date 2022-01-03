package com.app.adreal.timetable.authorization.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.adreal.timetable.R
import com.app.adreal.timetable.authorization.viewmodel.authViewModel
import com.app.adreal.timetable.constants.constants.Companion.clientID
import com.app.adreal.timetable.constants.constants.Companion.request_code
import com.app.adreal.timetable.databinding.FragmentLoginFragmentBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException


class login_fragment : Fragment() {

    lateinit var binding: FragmentLoginFragmentBinding

    lateinit var authViewModel: authViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            //authViewModel.firebase_signin(binding.email.text.toString(),binding.password.text.toString())
            googlesignin()
        }

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
            }
            catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("google auth", "Google sign in failed", e)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}