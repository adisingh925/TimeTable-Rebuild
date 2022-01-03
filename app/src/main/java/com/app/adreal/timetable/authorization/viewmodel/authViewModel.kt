package com.app.adreal.timetable.authorization.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.adreal.timetable.R
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRegistrar
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class authViewModel : ViewModel() {

    private var auth = Firebase.auth

    var registerstate = MutableLiveData<Boolean>()

    var loginstate = MutableLiveData<Boolean>()

    var passwordresetstate = MutableLiveData<Boolean>()

    fun firebase_register(email : String, password : String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    registerstate.postValue(true)
                } else {
                    registerstate.postValue(false)
                }
            }
        }
    }

    fun firebase_signin(email : String, password : String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{ task ->
                if(task.isSuccessful)
                {
                    loginstate.postValue(true)
                }
                else{
                    loginstate.postValue(false)
                }
            }
        }
    }

    fun firebase_reset_password(email : String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            auth.sendPasswordResetEmail(email).addOnCompleteListener{ task ->
                if(task.isSuccessful)
                {
                    passwordresetstate.postValue(true)
                }
                else
                {
                    passwordresetstate.postValue(false)
                }
            }
        }
    }

    fun firebaseAuthWithGoogle(token : String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            val credential = GoogleAuthProvider.getCredential(token, null)
            auth.signInWithCredential(credential).addOnCompleteListener() { task ->
                if(task.isSuccessful)
                {

                }
                else
                {

                }
            }
        }
    }
}