package com.app.adreal.timetable.daysdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.adreal.timetable.daysdatabase.model.friday_model

@Dao
interface fridayDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertdata(data : friday_model)

    @Delete
    fun delete(data : friday_model)

    @Query("delete from friday")
    fun deleteall()

    @Query("select * from friday where username is :uid")
    fun realalldata(uid : String) : LiveData<List<friday_model>>

    @Update
    fun update(data : friday_model)
}