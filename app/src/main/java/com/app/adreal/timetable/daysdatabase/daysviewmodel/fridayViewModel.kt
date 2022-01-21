package com.app.adreal.timetable.daysdatabase.daysviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.app.adreal.timetable.daysdatabase.model.friday_model
import com.app.adreal.timetable.userdatabase.user_database
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class fridayViewModel(application: Application) : AndroidViewModel(application) {

    private var auth = Firebase.auth

    val readalldata = user_database.getDatabase(application).fridaydao().realalldata(auth.uid.toString())

    fun insert(data : friday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).fridaydao().insertdata(data)
        }
    }

    fun delete(data : friday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).fridaydao().delete(data)
        }
    }

    fun deleteall()
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).fridaydao().deleteall()
        }
    }

    fun update(data : friday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).fridaydao().update(data)
        }
    }
}