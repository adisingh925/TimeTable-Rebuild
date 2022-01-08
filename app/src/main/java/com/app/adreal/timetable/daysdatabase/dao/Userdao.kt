package com.app.adreal.timetable.daysdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface userdao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertdata()
}