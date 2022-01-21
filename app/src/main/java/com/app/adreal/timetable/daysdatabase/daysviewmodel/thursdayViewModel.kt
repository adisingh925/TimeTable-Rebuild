package com.app.adreal.timetable.daysdatabase.daysviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.app.adreal.timetable.daysdatabase.model.thursday_model
import com.app.adreal.timetable.userdatabase.user_database
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class thursdayViewModel(application: Application) : AndroidViewModel(application) {

    private var auth = Firebase.auth

    val readalldata = user_database.getDatabase(application).thursdaydao().realalldata(auth.uid.toString())

    fun insert(data : thursday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).thursdaydao().insertdata(data)
        }
    }

    fun delete(data : thursday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).thursdaydao().delete(data)
        }
    }

    fun deleteall()
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).thursdaydao().deleteall()
        }
    }

    fun update(data : thursday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).thursdaydao().update(data)
        }
    }
}