package com.app.adreal.timetable.daysdatabase.daysviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.app.adreal.timetable.daysdatabase.model.dayModel
import com.app.adreal.timetable.daysdatabase.database.user_database
import com.app.adreal.timetable.daysdatabase.model.taskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class daysViewModel(application: Application) : AndroidViewModel(application) {

    val readalldata = user_database.getDatabase(application).dayDao().realalldata()

    val readAllTasks = user_database.getDatabase(application).dayDao().readAllTasks()

    fun insert(data : dayModel)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).dayDao().insertdata(data)
        }
    }

    fun delete(data : dayModel)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).dayDao().delete(data)
        }
    }

    fun deleteall()
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).dayDao().deleteall()
        }
    }

    fun update(data : dayModel)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).dayDao().update(data)
        }
    }

    fun insertTask(data : taskModel)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).dayDao().insertTask(data)
        }
    }

    fun updateTask(data : taskModel)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).dayDao().updateTask(data)
        }
    }

    fun deleteTask(data : taskModel)
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).dayDao().deleteTask(data)
        }
    }

    fun deleteAllTasks()
    {
        viewModelScope.launch(Dispatchers.IO) {
            user_database.getDatabase(getApplication()).dayDao().deleteAllTasks()
        }
    }
}