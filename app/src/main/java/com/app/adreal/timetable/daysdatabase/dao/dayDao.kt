package com.app.adreal.timetable.daysdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.adreal.timetable.daysdatabase.model.monday_model

@Dao
interface dayDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertdata(data : monday_model)

    @Delete
    fun delete(data : monday_model)

    @Query("delete from monday")
    fun deleteall()

    @Query("select * from monday where username is :uid")
    fun realalldata(uid : String) : LiveData<List<monday_model>>

    @Update
    fun update(data : monday_model)
}