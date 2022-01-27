package com.app.adreal.timetable.daysdatabase.daysviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.app.adreal.timetable.daysdatabase.model.tuesday_model
import com.app.adreal.timetable.userdatabase.user_database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class tuesdayViewModel(application: Application) : AndroidViewModel(application) {

    val readalldata = user_database.getDatabase(application).tuesdaydao().realalldata()

    fun insert(data : tuesday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).tuesdaydao().insertdata(data)
        }
    }

    fun delete(data : tuesday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).tuesdaydao().delete(data)
        }
    }

    fun deleteall()
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).tuesdaydao().deleteall()
        }
    }

    fun update(data : tuesday_model)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).tuesdaydao().update(data)
        }
    }
}