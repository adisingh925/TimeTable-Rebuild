package com.app.adreal.timetable.daysdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.adreal.timetable.daysdatabase.model.dayModel
import com.app.adreal.timetable.daysdatabase.model.taskModel

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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTask(data : taskModel)

    @Delete
    fun deleteTask(data : taskModel)

    @Update
    fun updateTask(data : taskModel)

    @Query("select * from tasks")
    fun readAllTasks() : LiveData<List<taskModel>>

    @Query("delete from tasks")
    fun deleteAllTasks()
}