package com.app.adreal.timetable.daysdatabase.daysviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.app.adreal.timetable.daysdatabase.model.saturday_model
import com.app.adreal.timetable.userdatabase.user_database
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class saturdayViewModel(application: Application) : AndroidViewModel(application) {

    private var auth = Firebase.auth

    val readalldata = user_database.getDatabase(application).saturdaydao().realalldata(auth.uid.toString())

    fun insert(data : saturday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).saturdaydao().insertdata(data)
        }
    }

    fun delete(data : saturday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).saturdaydao().delete(data)
        }
    }

    fun deleteall()
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).saturdaydao().deleteall()
        }
    }

    fun update(data : saturday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).saturdaydao().update(data)
        }
    }
}