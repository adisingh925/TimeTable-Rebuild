package com.app.adreal.timetable.userdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class userViewModel(application: Application) : AndroidViewModel(application) {

    //private var auth = Firebase.auth

    val readdata = user_database.getDatabase(application).userdao().read("aditya")

    val istableexists = user_database.getDatabase(getApplication()).userdao().isRowExist("aditya")

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