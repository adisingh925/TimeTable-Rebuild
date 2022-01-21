package com.app.adreal.timetable.daysdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.adreal.timetable.daysdatabase.model.wednesday_model

@Dao
interface wednesdayDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertdata(data : wednesday_model)

    @Delete
    fun delete(data : wednesday_model)

    @Query("delete from wednesday")
    fun deleteall()

    @Query("select * from wednesday where username is :uid")
    fun realalldata(uid : String) : LiveData<List<wednesday_model>>

    @Update
    fun update(data : wednesday_model)
}