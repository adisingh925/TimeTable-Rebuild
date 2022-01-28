package com.app.adreal.timetable.daysdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.adreal.timetable.daysdatabase.model.dayModel

@Dao
interface dayDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertdata(data : dayModel)

    @Delete
    fun delete(data : dayModel)

    @Query("delete from days")
    fun deleteall()

    @Query("select * from days")
    fun realalldata() : LiveData<List<dayModel>>

    @Update
    fun update(data : dayModel)
}