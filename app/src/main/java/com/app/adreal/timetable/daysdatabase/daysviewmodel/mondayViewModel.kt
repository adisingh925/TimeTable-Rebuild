package com.app.adreal.timetable.daysdatabase.daysviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.app.adreal.timetable.daysdatabase.model.monday_model
import com.app.adreal.timetable.userdatabase.user_database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class mondayViewModel(application: Application) : AndroidViewModel(application) {

    val readalldata = user_database.getDatabase(application).mondaydao().realalldata()

    fun insert(data : monday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).mondaydao().insertdata(data)
        }
    }

    fun delete(data : monday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).mondaydao().delete(data)
        }
    }

    fun deleteall()
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).mondaydao().deleteall()
        }
    }

    fun update(data : monday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).mondaydao().update(data)
        }
    }
}