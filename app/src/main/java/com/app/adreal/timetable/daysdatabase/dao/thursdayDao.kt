package com.app.adreal.timetable.daysdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.adreal.timetable.daysdatabase.model.thursday_model

@Dao
interface thursdayDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertdata(data : thursday_model)

    @Delete
    fun delete(data : thursday_model)

    @Query("delete from thursday")
    fun deleteall()

    @Query("select * from thursday where username is :uid")
    fun realalldata(uid : String) : LiveData<List<thursday_model>>

    @Update
    fun update(data : thursday_model)
}