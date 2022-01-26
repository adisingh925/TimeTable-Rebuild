package com.app.adreal.timetable.daysdatabase.daysviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.app.adreal.timetable.daysdatabase.model.wednesday_model
import com.app.adreal.timetable.userdatabase.user_database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class wednesdayViewModel(application: Application) : AndroidViewModel(application) {

    //private var auth = Firebase.auth

    val readalldata = user_database.getDatabase(application).wednesdaydao().realalldata("aditya")

    fun insert(data : wednesday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).wednesdaydao().insertdata(data)
        }
    }

    fun delete(data : wednesday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).wednesdaydao().delete(data)
        }
    }

    fun deleteall()
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).wednesdaydao().deleteall()
        }
    }

    fun update(data : wednesday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).wednesdaydao().update(data)
        }
    }
}