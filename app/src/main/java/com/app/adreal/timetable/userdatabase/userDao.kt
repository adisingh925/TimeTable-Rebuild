package com.app.adreal.timetable.userdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface userDao {

    @Insert
    fun insert(data : user_model)

    @Query("select * from user_info where id is 1")
    fun read() : LiveData<user_model?>

    @Update
    fun update(data : user_model)

    @Query("SELECT EXISTS(SELECT * FROM user_info)")
    fun isRowExist() : LiveData<Boolean>
}