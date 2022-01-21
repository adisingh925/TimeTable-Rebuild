package com.app.adreal.timetable.daysdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.adreal.timetable.daysdatabase.model.saturday_model

@Dao
interface saturdayDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertdata(data : saturday_model)

    @Delete
    fun delete(data : saturday_model)

    @Query("delete from saturday")
    fun deleteall()

    @Query("select * from saturday where username is :uid")
    fun realalldata(uid : String) : LiveData<List<saturday_model>>

    @Update
    fun update(data : saturday_model)
}