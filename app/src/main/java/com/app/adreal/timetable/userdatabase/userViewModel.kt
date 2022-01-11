package com.app.adreal.timetable.userdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class userViewModel(application: Application) : AndroidViewModel(application) {

    val readdata = user_database.getDatabase(application).userdao().read()

    val istableexists = user_database.getDatabase(getApplication()).userdao().isRowExist()

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