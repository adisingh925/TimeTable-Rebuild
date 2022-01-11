package com.app.adreal.timetable.userdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class userViewModel(application: Application) : AndroidViewModel(application) {

    private var auth = Firebase.auth

    val readdata = user_database.getDatabase(application).userdao().read(auth.uid.toString())

    val istableexists = user_database.getDatabase(getApplication()).userdao().isRowExist(auth.uid.toString())

    fun insert(data: user_model) {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).userdao().insert(data)
        }
    }

    fun update(data: user_model) {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).userdao().update(data)
        }
    }
}