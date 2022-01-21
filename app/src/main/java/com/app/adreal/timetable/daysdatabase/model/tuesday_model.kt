package com.app.adreal.timetable.daysdatabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tuesday")
data class tuesday_model(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "username") val username : String,
    @ColumnInfo(name = "start_time") val starttime : String,
    @ColumnInfo(name = "end_time") val endtime : String,
    @ColumnInfo(name = "subject") val subject : String,
)