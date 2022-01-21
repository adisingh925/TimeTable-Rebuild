package com.app.adreal.timetable.daysdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.adreal.timetable.daysdatabase.model.tuesday_model

@Dao
interface tuesdayDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertdata(data : tuesday_model)

    @Delete
    fun delete(data : tuesday_model)

    @Query("delete from tuesday")
    fun deleteall()

    @Query("select * from tuesday where username is :uid")
    fun realalldata(uid : String) : LiveData<List<tuesday_model>>

    @Update
    fun update(data : tuesday_model)
}